package com.monowai.mondrian.commands;

import static org.assertj.core.api.Assertions.assertThat;

import com.monowai.mondrian.Mondrian;
import com.monowai.mondrian.canvas.ConsoleCanvas;
import io.micronaut.test.annotation.MicronautTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

@MicronautTest
@CommandLine.Command
public class CommandFlowTest {
  @Inject
  private Mondrian mondrian;

  @Inject
  private ConsoleCanvas canvas;

  @Inject
  private CommandFactory commandFactory;

  @Test
  void is_AcceptanceCriteriaCorrect() {
    assertThat(mondrian).isNotNull();
    assertThat(canvas).isNotNull();
    assertThat(commandFactory).isNotNull();

    // Initialise PICO stuff, but don't enable commandLine input
    mondrian.run(false);
    new CommandLine(commandFactory).execute("c", "20", "4");
    new CommandLine(commandFactory).execute("l", "1", "2", "6", "2");
    new CommandLine(commandFactory).execute("l", "6", "3", "6", "4");
    new CommandLine(commandFactory).execute("r", "14", "1", "18", "3");
    new CommandLine(commandFactory).execute("b", "10", "3", "o");
    assertThat(canvas.getElements())
        .isNotNull().isEqualTo("----------------------\n"
        + "|oooooooooooooxxxxxoo|\n"
        + "|xxxxxxooooooox   xoo|\n"
        + "|     xoooooooxxxxxoo|\n"
        + "|     xoooooooooooooo|\n"
        + "----------------------");

    new CommandLine(commandFactory).execute("cls");
    // Should get a default canvas
    assertThat(canvas.getElements()).isEqualTo("----------------------\n"
        + "|                    |\n"
        + "|                    |\n"
        + "|                    |\n"
        + "|                    |\n"
        + "----------------------");
  }

}
