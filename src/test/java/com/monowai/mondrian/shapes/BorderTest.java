package com.monowai.mondrian.shapes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.Border;
import org.junit.jupiter.api.Test;

public class BorderTest {

  @Test
  void isCanvasDefaultsValid() {
    assertThat(new ConsoleCanvas()).isNotNull();
  }

  @Test
  void areHeightConstraintsGuarded() {
    assertThrows(IllegalArgumentException.class, ()
        -> new ConsoleCanvas(Border.builder().height(-10).build()));
    assertThrows(IllegalArgumentException.class, ()
        -> new ConsoleCanvas(Border.builder().height(0).build()));

  }

  @Test
  void areWidthConstraintsGuarded() {
    assertThrows(IllegalArgumentException.class, ()
        -> new ConsoleCanvas(Border.builder().width(-10).build()));
    assertThrows(IllegalArgumentException.class, ()
        -> new ConsoleCanvas(Border.builder().width(0).build()));
  }


}
