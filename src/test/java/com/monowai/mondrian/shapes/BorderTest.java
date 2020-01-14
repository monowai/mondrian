package com.monowai.mondrian.shapes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.Border;
import org.junit.jupiter.api.Test;

public class BorderTest {

  @Test
  void is_CanvasDefaultsValid() {
    assertThat(Border.builder().build().isValid()).isTrue();
  }

  @Test
  void are_HeightConstraintsGuarded() {
    Border illegalHeight = Border.builder().height(-10).build();
    assertThrows(IllegalArgumentException.class, illegalHeight::isValid);
    Border zeroHeight = Border.builder().height(0).build();
    assertThrows(IllegalArgumentException.class, zeroHeight::isValid);
    // Constraints should be verified when creating a canvas
    assertThrows(IllegalArgumentException.class, () -> new ConsoleCanvas(illegalHeight));
  }

  @Test
  void are_WidthConstraintsGuarded() {
    Border illegalWidth = Border.builder().width(-10).build();
    assertThrows(IllegalArgumentException.class, illegalWidth::isValid);
    Border zeroWidth = Border.builder().width(0).build();
    assertThrows(IllegalArgumentException.class, zeroWidth::isValid);
  }


}
