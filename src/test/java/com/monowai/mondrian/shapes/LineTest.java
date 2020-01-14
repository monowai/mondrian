package com.monowai.mondrian.shapes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.Border;
import com.monowai.mondrian.model.LineData;
import org.junit.jupiter.api.Test;

public class LineTest {

  @Test
  void is_InvalidLineThrowing() {
    Border validBorder = Border.builder().build();
    LineData illegalX = LineData.builder()
        .posX(-1)
        .posY(2)
        .build();
    assertThrows(IllegalArgumentException.class, () -> illegalX.isValid(validBorder));
    LineData illegalY = LineData.builder()
        .posX(1)
        .posY(-2)
        .build();
    assertThrows(IllegalArgumentException.class, () -> illegalY.isValid(validBorder));

    assertThrows(IllegalArgumentException.class, () -> new ConsoleCanvas(validBorder)
        .draw(illegalY));
  }

  @Test
  void is_LineRenderingInCanvas() {
    Border validBorder = Border.builder().build();
    LineData lineData = LineData.builder()
        .posX(1)
        .posY(2)
        .endX(6)
        .endY(2)
        .build();

    assertThat(lineData.isValid(validBorder));

    ConsoleCanvas canvas = new ConsoleCanvas(validBorder);
    canvas.setPointGlyph('.');
    canvas.draw(lineData);
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
    Border validBorder = Border.builder().build();
    LineData lineData = LineData.builder()
        .posX(1)
        .posY(2)
        .endX(1)
        .endY(20)
        .build();

    assertThat(lineData.isValid(validBorder));

    ConsoleCanvas canvas = new ConsoleCanvas(validBorder);
    canvas.setPointGlyph('.');
    canvas.draw(lineData);
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
    Border validBorder = Border.builder().build();
    LineData lineData = LineData.builder()
        .posX(1)
        .posY(2)
        .endX(50)
        .endY(2)
        .build();

    assertThat(lineData.isValid(validBorder));

    ConsoleCanvas canvas = new ConsoleCanvas(validBorder);
    canvas.setPointGlyph('.');
    canvas.draw(lineData);
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
