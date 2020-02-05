package com.monowai.mondrian.shapes;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.Border;
import com.monowai.mondrian.model.LineData;

public class Line implements Shape {

  private ConsoleCanvas canvas;
  private LineData lineData;

  /**
   * Instance of a Line supporting text output.
   * Throws an exception if the data does not fit on the default canvas.
   *
   * @param lineData properties to use in rendering
   */
  public Line(LineData lineData) {
    this(new ConsoleCanvas(), lineData);
  }

  /**
   * Instance of a Line supporting text output.
   * Throws an exception if the data does not fit on the canvas.
   *
   * @param canvas   text Console
   * @param lineData properties to use in rendering
   */
  public Line(ConsoleCanvas canvas, LineData lineData) {
    this.canvas = canvas;
    this.lineData = lineData;
    if (!isValid()) {
      throw new IllegalArgumentException("Unable to draw that line");
    }
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

  /**
   * False if it will not fit.
   */
  public boolean isValid() {
    // horizontal/vertical only
    boolean isNotDiagonal = ((lineData.getPosX() == lineData.getEndX())
        | (lineData.getEndY() == lineData.getPosY()));

    return isNotDiagonal && lineData.getPosX() >= 1
        && lineData.getPosX() < canvas.getBorder().getWidth()
        && lineData.getPosY() >= 1
        && lineData.getPosY() <= canvas.getBorder().getHeight();
  }

}
