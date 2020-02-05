package com.monowai.mondrian.model;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.shapes.Fill;
import com.monowai.mondrian.shapes.Shape;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FillData implements ShapeData {
  private int posX;
  private int posY;
  @Builder.Default
  private char fill = '.';

  @Override
  public Shape create(ConsoleCanvas canvas) {
    return new Fill(canvas, this);
  }
}
