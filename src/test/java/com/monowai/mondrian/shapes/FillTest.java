package com.monowai.mondrian.shapes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.Border;
import com.monowai.mondrian.model.FillData;
import com.monowai.mondrian.model.RectangleData;
import org.junit.jupiter.api.Test;

public class FillTest {
  @Test
  void is_InvalidBucketFillThrowing() {
    Border validBorder = Border.builder().build();
    FillData illegalX = FillData.builder()
        .posX(-1)
        .posY(2)
        .build();
    assertThrows(IllegalArgumentException.class, () -> illegalX.isValid(validBorder));
    FillData illegalY = FillData.builder()
        .posX(1)
        .posY(-2)
        .build();
    assertThrows(IllegalArgumentException.class, () -> illegalY.isValid(validBorder));

    assertThrows(IllegalArgumentException.class, () -> new ConsoleCanvas(validBorder)
        .draw(illegalY));
  }

  @Test
  void is_RectangleBucketFilled() {
    Border border = Border.builder().build();
    ConsoleCanvas canvas = new ConsoleCanvas(border);
    canvas.draw(RectangleData.builder().posX(8).posY(1).endX(14).endY(4).build());
    FillData fillData = FillData.builder().posX(9).posY(2).fill('.').build();
    assertThat(fillData.isValid(border)).isTrue();
    canvas.draw(fillData);

    assertThat(canvas.getElements())
        .isEqualTo("----------------------\n"
            + "|       xxxxxxx      |\n"
            + "|       x.....x      |\n"
            + "|       x.....x      |\n"
            + "|       xxxxxxx      |\n"
            + "----------------------");

  }
}
