package com.monowai.mondrian.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LineData implements ShapeData {
  private int posX;
  private int posY;
  private int endX;
  private int endY;

  /**
   * Throws an exception if dimensions are invalid.
   *
   * @return true if valid
   */
  public boolean isValid(Border border) {
    if (posX < 1 || posX >= border.width || posY < 1 || posY > border.height) {
      throw new IllegalArgumentException("X,Y coordinates are outside of canvas");
    }
    return true;
  }

}
