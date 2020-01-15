package com.monowai.mondrian.shapes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.LineData;
import org.junit.jupiter.api.Test;

public class LineTest {

  @Test
  void is_InvalidLineThrowing() {
    assertThrows(IllegalArgumentException.class, () ->
        new Line(LineData.builder()
            .posX(-1)
            .posY(2)
            .build()));

    assertThrows(IllegalArgumentException.class, () ->
        new Line(LineData.builder()
            .posX(1)
            .posY(-2)
            .build()));

  }

  @Test
  void is_LineRenderingInCanvas() {

    ConsoleCanvas canvas = new ConsoleCanvas();
    canvas.setPointGlyph('.');
    canvas.draw(LineData.builder()
        .posX(1)
        .posY(2)
        .endX(6)
        .endY(2)
        .build());
    String borderAndLine = canvas.getElements();
    assertThat(borderAndLine)
        .isEqualTo("----------------------\n"
            + "|                    |\n"
            + "|......              |\n"
            + "|                    |\n"
            + "|                    |\n"
            + "----------------------");

  }

  @Test
  void is_VerticalLineTrimmedToFit() {

    ConsoleCanvas canvas = new ConsoleCanvas();
    canvas.setPointGlyph('.');
    canvas.draw(LineData.builder()
        .posX(1)
        .posY(2)
        .endX(1)
        .endY(20)
        .build());
    String borderAndLine = canvas.getElements();
    assertThat(borderAndLine)
        .isEqualTo("----------------------\n"
            + "|                    |\n"
            + "|.                   |\n"
            + "|.                   |\n"
            + "|.                   |\n"
            + "----------------------");

  }


  @Test
  void is_HorizontalLineTrimmedToFit() {

    ConsoleCanvas canvas = new ConsoleCanvas();
    canvas.setPointGlyph('.');
    canvas.draw(LineData.builder()
        .posX(1)
        .posY(2)
        .endX(50)
        .endY(2)
        .build());
    String borderAndLine = canvas.getElements();
    assertThat(borderAndLine)
        .isEqualTo("----------------------\n"
            + "|                    |\n"
            + "|....................|\n"
            + "|                    |\n"
            + "|                    |\n"
            + "----------------------");

  }

}
