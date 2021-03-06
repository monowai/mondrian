package com.monowai.mondrian.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
/*
  Usable dimensions of the canvas - basically, internal drawing space.
 */
public class Border {
  @Builder.Default
  private int width = 20;
  @Builder.Default
  private int height = 4;
  @Builder.Default
  private char[] horizontalEdge = new char[] {'-'};

  @Builder.Default
  private char[] verticalEdge = new char[] {'|'};

}
