package com.monowai.mondrian.canvas;

import com.monowai.mondrian.model.Border;
import com.monowai.mondrian.model.ShapeData;

public interface Canvas {
  /**
   * The canvas on which elements will be drawn.
   *
   * @param border dimensions
   */
  void createCanvas(Border border);

  /**
   * Update the canvas.
   *
   * @param element to register
   */
  void draw(ShapeData element);

  /**
   * Render canvas elements to the target device.
   */
  void render();

  /**
   * Clear down the canvas.
   */
  void clear();
}
