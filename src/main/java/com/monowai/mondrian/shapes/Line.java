package com.monowai.mondrian.shapes;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.Border;
import com.monowai.mondrian.model.LineData;

public class Line implements Shape {

  private ConsoleCanvas canvas;
  private LineData lineData;

  /**
   * Instance of a Line supporting text output.
   * Throws an exception if the data does not fit on the canvas.
   *
   * @param canvas text Console
   * @param lineData properties to use in rendering
   */
  public Line(ConsoleCanvas canvas, LineData lineData) {
    lineData.isValid(canvas.getBorder());
    this.canvas = canvas;
    this.lineData = lineData;
  }

  @Override
  public void draw() {
    //row by row
    Border border = canvas.getBorder();
    for (int row = lineData.getPosY() - 1; row <= lineData.getEndY() - 1
        && row < border.getHeight(); row++) {
      //col by col
      for (int col = lineData.getPosX() - 1; col <= lineData.getEndX() - 1
          && col < border.getWidth(); col++) {
        canvas.getCanvasArray()[row][col] = canvas.getPointGlyph();
      }
    }
  }
}
