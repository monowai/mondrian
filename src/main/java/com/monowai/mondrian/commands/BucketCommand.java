package com.monowai.mondrian.commands;

import com.monowai.mondrian.model.FillData;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "B", aliases = {"b", "bucket fill"}, mixinStandardHelpOptions = true,
    description = "Perform a bucket fill - B 10 3 o", version = "1.0")
public class BucketCommand implements Runnable {
  @CommandLine.ParentCommand
  private CommandFactory commandFactory;

  @Parameters(index = "0", description = "x coordinate")
  private int posX;
  @Parameters(index = "1", description = "y coordinate")
  private int posY;
  @Parameters(index = "2", description = "fill")
  private char fill;

  @Override
  public void run() {
    commandFactory.getCanvas()
        .draw(FillData.builder()
            .posX(posX)
            .posY(posY)
            .fill(fill)
            .build());
    commandFactory.getCanvas().render();
  }

}
