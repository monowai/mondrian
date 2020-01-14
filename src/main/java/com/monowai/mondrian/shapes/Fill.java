package com.monowai.mondrian.shapes;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.FillData;
import com.monowai.mondrian.model.Point;
import java.util.Stack;

public class Fill implements Shape {
  private ConsoleCanvas canvas;
  private FillData element;

  /**
   * Instance of a Bucket Fill supporting text output.
   * Throws an exception if the data does not fit on the canvas.
   *
   * @param canvas text Console
   * @param fillData properties to use in rendering
   */
  public Fill(ConsoleCanvas canvas, FillData fillData) {
    fillData.isValid(canvas.getBorder());
    this.canvas = canvas;
    this.element = fillData;
  }

  @Override
  public void draw() {
    element.isValid(canvas.getBorder());
    int y = element.getPosY();
    int x = element.getPosX();
    char[][] canvasArray = canvas.getCanvasArray();
    char originalChar = canvasArray[y - 1][x - 1];
    Stack<Point> stack = new Stack<>();
    stack.add(new Point(y - 1, x - 1));
    //BFS traversal
    while (!stack.isEmpty()) {
      Point pop = stack.pop();
      if (canvasArray[pop.getPosY()][pop.getPosX()] == originalChar) {
        canvasArray[pop.getPosY()][pop.getPosX()] = element.getFill();
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
}
