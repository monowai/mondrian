package com.monowai.mondrian;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.commands.CommandFactory;
import io.micronaut.configuration.picocli.PicocliRunner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.inject.Inject;
import org.jline.builtins.Builtins;
import org.jline.builtins.Completers;
import org.jline.builtins.Options.HelpException;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.MaskingCallback;
import org.jline.reader.ParsedLine;
import org.jline.reader.Parser;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.shell.jline3.PicocliCommands;

@Command(name = "com.monowai.mondrian", description = "...",
    mixinStandardHelpOptions = true)
public class Mondrian implements Runnable {

  @Inject
  private ConsoleCanvas canvas;
  @Inject
  private CommandFactory commandFactory;

  public static void main(String[] args) throws Exception {
    PicocliRunner.run(Mondrian.class, args);
  }

  /**
   * Setup Pico and the command factory.
   */
  public void run() {
    run(true);
  }

  /**
   * Support testing.
   *
   * @param withCommandLineReader should we monitor for CLI input
   */
  public void run(boolean withCommandLineReader) {
    try {
      // set up JLine built-in commands
      Path workDir = Paths.get("");
      Builtins builtins = new Builtins(workDir, null, null);
      builtins.rename(org.jline.builtins.Builtins.Command.TTOP, "top");
      builtins.alias("zle", "widget");
      builtins.alias("bindkey", "keymap");
      Completers.SystemCompleter systemCompleter = builtins.compileCompleters();

      PicocliCommands picocliCommands = new PicocliCommands(workDir,
          new CommandLine(commandFactory));
      systemCompleter.add(picocliCommands.compileCompleters());
      systemCompleter.compile();
      Terminal terminal = TerminalBuilder.builder().build();
      LineReader reader = LineReaderBuilder.builder()
          .terminal(terminal)
          .completer(systemCompleter)
          .parser(new DefaultParser())
          .variable(LineReader.LIST_MAX, 50)   // max tab completion candidates
          .build();
      builtins.setLineReader(reader);
      canvas.setReader(reader);
      if (withCommandLineReader) {
        runCommandLine(builtins, terminal, reader);
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }

  }

  private void runCommandLine(Builtins builtins, Terminal terminal, LineReader reader) {
    String prompt = "mondrian$ ";
    String rightPrompt = null;

    // start the shell and process input until the user quits with Ctl-D

    while (true) {
      try {
        String line = reader.readLine(prompt, rightPrompt, (MaskingCallback) null, null);
        if (line.matches("^\\s*#.*")) {
          continue;
        }
        ParsedLine pl = reader.getParser().parse(line, 0);
        String[] arguments = pl.words().toArray(new String[0]);
        String command = Parser.getCommand(pl.word());
        if (builtins.hasCommand(command)) {
          builtins.execute(command, Arrays.copyOfRange(arguments, 1, arguments.length),
              System.in, System.out, System.err);
        } else {
          new CommandLine(commandFactory).execute(arguments);
        }
      } catch (HelpException e) {
        HelpException.highlight(e.getMessage(), HelpException.defaultStyle()).print(terminal);
      } catch (UserInterruptException e) {
        // Ignore
      } catch (EndOfFileException e) {
        return;
      } catch (Exception e) {
        AttributedStringBuilder asb = new AttributedStringBuilder();
        asb.append(e.getMessage(), AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));
        asb.toAttributedString().println(terminal);
      }
    }
  }


}
