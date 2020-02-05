package com.monowai.mondrian.model;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.shapes.Line;
import com.monowai.mondrian.shapes.Shape;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LineData implements ShapeData {
  private int posX;
  private int posY;
  private int endX;
  private int endY;

  @Override
  public Shape create(ConsoleCanvas canvas) {
    return new Line(canvas, this);
  }
}
