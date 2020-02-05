package com.monowai.mondrian.model;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.shapes.Shape;

public interface ShapeData {

  Shape create(ConsoleCanvas canvas);
}
