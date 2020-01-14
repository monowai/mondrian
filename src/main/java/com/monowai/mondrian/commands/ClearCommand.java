package com.monowai.mondrian.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

@Command(name = "cls", aliases = "clear", mixinStandardHelpOptions = true,
    description = "Clears the canvas", version = "1.0")
public class ClearCommand implements Runnable {

  @ParentCommand
  private CommandFactory commandFactory;

  @Override
  public void run() {
    commandFactory.getCanvas().clear();
  }
}
