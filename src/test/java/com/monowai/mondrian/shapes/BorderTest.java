package com.monowai.mondrian.shapes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.monowai.mondrian.canvas.ConsoleCanvas;
import com.monowai.mondrian.model.Border;
import org.junit.jupiter.api.Test;

public class BorderTest {

  @Test
  void is_CanvasDefaultsValid() {
    assertThat(new ConsoleCanvas()).isNotNull();
  }

  @Test
  void are_HeightConstraintsGuarded() {
    assertThrows(IllegalArgumentException.class, ()
        -> new ConsoleCanvas(Border.builder().height(-10).build()));
    assertThrows(IllegalArgumentException.class, ()
        -> new ConsoleCanvas(Border.builder().height(0).build()));

  }

  @Test
  void are_WidthConstraintsGuarded() {
    assertThrows(IllegalArgumentException.class, ()
        -> new ConsoleCanvas(Border.builder().width(-10).build()));
    assertThrows(IllegalArgumentException.class, ()
        -> new ConsoleCanvas(Border.builder().width(0).build()));
  }


}
