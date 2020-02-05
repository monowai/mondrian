package com.monowai.mondrian.shapes;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.LineData;
import com.monowai.mondrian.model.RectangleData;

public class Rectangle implements Shape {
  private RectangleData rectangleData;
  private ConsoleCanvas canvas;

  public Rectangle(ConsoleCanvas canvas, RectangleData rectangleData) {
    this.rectangleData = rectangleData;
    this.canvas = canvas;
  }

  @Override
  public void draw() {
    int x1 = rectangleData.getPosX();
    int y1 = rectangleData.getPosY();
    int x2 = rectangleData.getEndX();
    int y2 = rectangleData.getEndY();
    //top edge
    LineData.builder()
        .posX(x1)
        .posY(y1)
        .endX(x2)
        .endY(y1)
        .build().create(canvas).draw();

    //right edge
    LineData.builder()
        .posX(x1)
        .posY(y1)
        .endX(x1)
        .endY(y2)
        .build().create(canvas).draw();

    //bottom edge
    LineData.builder()
        .posX(x2)
        .posY(y1)
        .endX(x2)
        .endY(y2)
        .build().create(canvas).draw();

    //right edge
    LineData.builder()
        .posX(x1)
        .posY(y2)
        .endX(x2)
        .endY(y2)
        .build().create(canvas).draw();

  }
}
