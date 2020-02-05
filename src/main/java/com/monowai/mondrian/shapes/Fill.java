package com.monowai.mondrian.shapes;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.Border;
import com.monowai.mondrian.model.FillData;
import com.monowai.mondrian.model.Point;
import java.util.Stack;

public class Fill implements Shape {
  private ConsoleCanvas canvas;
  private FillData fillData;

  /**
   * Instance of a Bucket Fill supporting text output.
   * Throws an exception if the data does not fit on the default canvas.
   *
   * @param fillData properties to use in rendering
   */
  public Fill(FillData fillData) {
    this(new ConsoleCanvas(), fillData);
  }

  /**
   * Instance of a Bucket Fill supporting text output.
   * Throws an exception if the data does not fit on the canvas.
   *
   * @param canvas   text Console
   * @param fillData properties to use in rendering
   */
  public Fill(ConsoleCanvas canvas, FillData fillData) {
    this.canvas = canvas;
    this.fillData = fillData;
    isValid();
  }

  @Override
  public void draw() {
    isValid();
    int y = fillData.getPosY();
    int x = fillData.getPosX();
    char[][] canvasArray = canvas.getCanvasArray();
    char originalChar = canvasArray[y - 1][x - 1];
    // Anything to do?
    if (originalChar == fillData.getFill()) {
      return;
    }
    Stack<Point> stack = new Stack<>();
    stack.add(new Point(y - 1, x - 1));
    //BFS traversal
    while (!stack.isEmpty()) {
      Point pop = stack.pop();
      if (canvasArray[pop.getPosY()][pop.getPosX()] == originalChar) {
        canvasArray[pop.getPosY()][pop.getPosX()] = fillData.getFill();
      }
      if (pop.getPosY() - 1 >= 0 && canvasArray[pop.getPosY() - 1][pop.getPosX()] == originalChar) {
        stack.add(new Point(pop.getPosY() - 1, pop.getPosX()));
      }
      if (pop.getPosY() + 1 < canvas.getBorder().getHeight()
          && canvasArray[pop.getPosY() + 1][pop.getPosX()] == originalChar) {
        stack.add(new Point(pop.getPosY() + 1, pop.getPosX()));
      }
      if (pop.getPosX() - 1 >= 0
          && canvasArray[pop.getPosY()][pop.getPosX() - 1] == originalChar) {
        stack.add(new Point(pop.getPosY(), pop.getPosX() - 1));
      }
      if (pop.getPosX() + 1 < canvas.getBorder().getWidth()
          && canvasArray[pop.getPosY()][pop.getPosX() + 1] == originalChar) {
        stack.add(new Point(pop.getPosY(), pop.getPosX() + 1));
      }
    }
  }

  /**
   * Determine if the bucket fill parameters are valid withing borders.
   */
  public void isValid() {
    Border border = canvas.getBorder();
    int posX = fillData.getPosX();
    int posY = fillData.getPosY();
    if (posX < 1 || posX >= border.getWidth() || posY < 1 || posY >= border.getHeight()) {
      throw new IllegalArgumentException(
          String.format("X,Y coordinates are outside of canvas %s", border.toString()));
    }
  }


}
