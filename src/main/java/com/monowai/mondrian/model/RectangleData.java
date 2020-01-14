package com.monowai.mondrian.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RectangleData implements ShapeData {
  private int posX;
  private int posY;
  private int endX;
  private int endY;

}
