package com.monowai.mondrian.commands;

import com.monowai.mondrian.canvas.Canvas;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.Data;
import picocli.CommandLine;

@CommandLine.Command(name = "",
    description = {
        "Example line drawing application"},
    footer = {"", "Press Ctl-D to exit."},
    subcommands = {
        CanvasCommand.class,
        LineCommand.class,
        RectangleCommand.class,
        BucketCommand.class,
        ClearCommand.class,
        CommandLine.HelpCommand.class})
@Data
@Singleton
public class CommandFactory {
  private Canvas canvas;

  @Inject
  void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }

}
