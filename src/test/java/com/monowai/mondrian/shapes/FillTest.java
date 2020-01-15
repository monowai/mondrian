package com.monowai.mondrian.shapes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.FillData;
import com.monowai.mondrian.model.RectangleData;
import org.junit.jupiter.api.Test;

public class FillTest {
  @Test
  void isInvalidBucketFillThrowing() {
    assertThrows(IllegalArgumentException.class, () -> new Fill(FillData.builder()
        .posX(-1)
        .posY(2)
        .build()));
    assertThrows(IllegalArgumentException.class, () -> new Fill(FillData.builder()
        .posX(1)
        .posY(-2)
        .build()));
  }

  @Test
  void isRectangleBucketFilled() {
    ConsoleCanvas canvas = new ConsoleCanvas();
    canvas.draw(RectangleData.builder().posX(8).posY(1).endX(14).endY(4).build());
    FillData fillData = FillData.builder().posX(9).posY(2).fill('.').build();
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
