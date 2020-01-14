package com.monowai.mondrian.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FillData implements ShapeData {
  private int posX;
  private int posY;
  @Builder.Default
  private char fill = '.';

  /**
   * Determine if the bucket fill parameters are valid withing borders.
   *
   * @param border to compare with
   */
  public boolean isValid(Border border) {
    if (posX < 1 || posX >= border.getWidth() || posY < 1 || posY >= border.getHeight()) {
      throw new IllegalArgumentException(
          String.format("X,Y coordinates are outside of canvas %s", border.toString()));
    }
    return true;
  }


}
