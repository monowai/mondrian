package com.monowai.mondrian.shapes;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.FillData;
import com.monowai.mondrian.model.LineData;
import com.monowai.mondrian.model.RectangleData;
import com.monowai.mondrian.model.ShapeData;

public class ShapeFactory {
  /**
   * Factory to return an Element that will implement the render functionality
   * to the canvas.
   *
   * @param canvas a valid canvas
   * @param shape  Properties to use for the element
   * @return flyweight to do the render
   */
  public Shape get(ConsoleCanvas canvas, ShapeData shape) {
    if (shape == null) {
      throw new IllegalArgumentException("No shape to draw");
    }
    if (shape instanceof LineData) {
      return new Line(canvas, (LineData) shape);
    } else if (shape instanceof RectangleData) {
      return new Rectangle(canvas, (RectangleData) shape);
    } else if (shape instanceof FillData) {
      return new Fill(canvas, (FillData) shape);
    }
    throw new IllegalArgumentException("Unable to render a shape for " + shape);
  }
}
