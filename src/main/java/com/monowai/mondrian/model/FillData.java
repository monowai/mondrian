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

}
