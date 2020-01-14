package com.monowai.mondrian.commands;

import com.monowai.mondrian.model.LineData;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "L", aliases = {"l", "line"}, mixinStandardHelpOptions = true,
    description = "Create a line - L 1 2 6 2", version = "1.0")
public class LineCommand implements Runnable {
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
        .draw(LineData.builder()
            .posX(posX)
            .posY(posY)
            .endX(endX)
            .endY(endY)
            .build());
    commandFactory.getCanvas().render();
  }

}
