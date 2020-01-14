package com.monowai.mondrian.canvas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.monowai.mondrian.model.Border;
import org.junit.jupiter.api.Test;

public class CanvasTest {

  @Test
  void is_IllegalCanvasPropsThrowing() {
    Canvas canvas = new ConsoleCanvas();
    assertThrows(IllegalArgumentException.class, canvas::render);
  }

  @Test
  void is_RenderExceptionThrowing() {
    ConsoleCanvas canvas = new ConsoleCanvas(Border.builder().build());
    assertThrows(IllegalArgumentException.class, canvas::render);
  }

  @Test
  void is_NullElementExceptionThrowing() {
    ConsoleCanvas canvas = new ConsoleCanvas(Border.builder().build());
    assertThrows(IllegalArgumentException.class, () -> canvas.draw(null));
  }


  @Test
  void is_DefaultCanvasRendering() {
    ConsoleCanvas canvas = new ConsoleCanvas();
    String result = canvas.getElements();
    assertThat(result).isEqualTo("----------------------\n"
        + "|                    |\n"
        + "|                    |\n"
        + "|                    |\n"
        + "|                    |\n"
        + "----------------------");

  }

  @Test
  void is_CanvasClearing() {
    ConsoleCanvas canvas = new ConsoleCanvas(Border.builder().build());
    canvas.clear();
    assertThat(canvas.getElements())
        .isEqualTo("----------------------\n"
        + "|                    |\n"
        + "|                    |\n"
        + "|                    |\n"
        + "|                    |\n"
        + "----------------------");

  }

}
