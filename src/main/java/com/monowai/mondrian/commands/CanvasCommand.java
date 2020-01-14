package com.monowai.mondrian.commands;

import com.monowai.mondrian.model.Border;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "C", aliases = {"c", "canvas"}, mixinStandardHelpOptions = true,
    description = "Create a Canvas - C 20 4", version = "1.0")
public class CanvasCommand implements Runnable {

  @CommandLine.ParentCommand
  private CommandFactory commandFactory;

  @Parameters(index = "0", description = "Width")
  private int width;
  @Parameters(index = "1", description = "Height")
  private int height;


  @Override
  public void run() {
    commandFactory.getCanvas().createCanvas(Border.builder()
        .height(height)
        .width(width)
        .build());

    commandFactory.getCanvas().render();
  }


}
