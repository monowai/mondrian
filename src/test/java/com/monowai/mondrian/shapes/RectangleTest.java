package com.monowai.mondrian.shapes;

import static org.assertj.core.api.Assertions.assertThat;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.Border;
import com.monowai.mondrian.model.RectangleData;
import org.junit.jupiter.api.Test;

public class RectangleTest {

  @Test
  void is_RectangleRenderingInCanvas() {
    Border validBorder = Border.builder().build();
    //R 14 1 18 3
    RectangleData rectangleData = RectangleData.builder()
        .posX(14)
        .posY(1)
        .endX(18)
        .endY(3)
        .build();

    ConsoleCanvas canvas = new ConsoleCanvas(validBorder);
    canvas.setPointGlyph('.');
    canvas.draw(rectangleData);
    String borderAndLine = canvas.getElements();
    assertThat(borderAndLine)
        .isEqualTo("----------------------\n"
            + "|             .....  |\n"
            + "|             .   .  |\n"
            + "|             .....  |\n"
            + "|                    |\n"
            + "----------------------");

  }


}
