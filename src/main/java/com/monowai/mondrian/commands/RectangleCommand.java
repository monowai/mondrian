package com.monowai.mondrian.commands;

import com.monowai.mondrian.model.RectangleData;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "R", aliases = {"r", "rectangle"}, mixinStandardHelpOptions = true,
    description = "Create a rectangle - R 14 1 18 3", version = "1.0")
public class RectangleCommand implements Runnable {
  @CommandLine.ParentCommand
  private CommandFactory commandFactory;

  @Parameters(index = "0", description = "x pos")
  private int posX;
  @Parameters(index = "1", description = "y pos")
  private int posY;
  @Parameters(index = "2", description = "x end")
  private int endX;
  @Parameters(index = "3", description = "y end")
  private int endY;


  @Override
  public void run() {
    commandFactory.getCanvas()
        .draw(RectangleData.builder()
            .posX(posX)
            .posY(posY)
            .endX(endX)
            .endY(endY)
            .build());
    commandFactory.getCanvas().render();
  }

}
