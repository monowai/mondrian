package com.monowai.mondrian.shapes;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.ShapeData;
import org.junit.jupiter.api.Test;

public class InvalidShapeTest {
  @Test
  void is_InvalidShapeHandled() {
    assertThrows(IllegalArgumentException.class, () ->
        new ConsoleCanvas().draw(new InvalidShape()));
  }

  static class InvalidShape implements ShapeData {

  }
}
