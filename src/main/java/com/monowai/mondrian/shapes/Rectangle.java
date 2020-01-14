package com.monowai.mondrian.shapes;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.LineData;
import com.monowai.mondrian.model.RectangleData;

public class Rectangle implements Shape {
  private RectangleData rectangleData;
  private ConsoleCanvas canvas;
  private ShapeFactory shapeFactory = new ShapeFactory();

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
    shapeFactory.get(canvas, LineData.builder()
        .posX(x1)
        .posY(y1)
        .endX(x2)
        .endY(y1)
        .build()).draw();

    //right edge
    shapeFactory.get(canvas, LineData.builder()
        .posX(x1)
        .posY(y1)
        .endX(x1)
        .endY(y2)
        .build()).draw();

    //bottom edge
    shapeFactory.get(canvas, LineData.builder()
        .posX(x2)
        .posY(y1)
        .endX(x2)
        .endY(y2)
        .build()).draw();

    //right edge
    shapeFactory.get(canvas, LineData.builder()
        .posX(x1)
        .posY(y2)
        .endX(x2)
        .endY(y2)
        .build()).draw();

  }
}
