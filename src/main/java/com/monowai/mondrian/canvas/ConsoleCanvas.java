package com.monowai.mondrian.canvas;

import com.monowai.mondrian.model.Border;
import com.monowai.mondrian.model.ShapeData;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Singleton;
import org.jline.reader.LineReader;
import org.jline.reader.impl.LineReaderImpl;

@Singleton
public class ConsoleCanvas implements Canvas {

  private PrintWriter out;
  private LineReaderImpl lineReader;
  private Border border;
  private char[][] canvasArray;
  private String horizontalEdge;
  private char pointGlyph = 'x';

  public ConsoleCanvas() {
    createCanvas(Border.builder().build());
  }

  public ConsoleCanvas(Border canvasBorder) {
    this();
    createCanvas(canvasBorder);
  }

  public void setReader(LineReader lineReader) {
    this.lineReader = (LineReaderImpl) lineReader;
    this.out = lineReader.getTerminal().writer();
  }

  /**
   * Init the canvas with dimensions so that elements can be added.
   *
   * @param border dimensions
   */
  public void createCanvas(Border border) {
    if (border.getHeight() <= 0 || border.getWidth() <= 0) {
      throw new IllegalArgumentException("Invalid dimensions");
    }

    this.border = border;
    canvasArray = new char[border.getHeight()][border.getWidth()];
    Arrays.stream(canvasArray).forEach(chars -> Arrays.fill(chars, ' '));
    horizontalEdge = Stream.generate(() -> String.valueOf(border.getHorizontalEdge()))
        .limit(border.getWidth() + 2).collect(Collectors.joining());
  }

  /**
   * Draw on the canvas.
   *
   * @param element Shape to add
   */
  @Override
  public void draw(ShapeData element) {
    isReady("No canvas has yet been created");
    if (element == null) {
      throw new IllegalArgumentException("No element to draw");
    }
    element.create(this).draw();
  }

  @Override
  public void clear() {
    this.border = null;
    this.canvasArray = null;
    // Create a default canvas
    createCanvas(Border.builder().build());
    if (lineReader != null) {
      lineReader.clearScreen();
    }
  }

  /**
   * Formatted elements.
   *
   * @return representation of the canvas to render
   */
  public String getElements() {
    isReady("No canvas has been prepared!");
    StringBuilder builder = new StringBuilder();
    builder.append(horizontalEdge).append("\n");
    for (int i = 0; i < border.getHeight(); i++) {
      builder.append(border.getVerticalEdge());
      for (int j = 0; j < border.getWidth(); j++) {
        builder.append(canvasArray[i][j]);
      }
      builder.append(border.getVerticalEdge());
      builder.append("\n");
    }
    builder.append(horizontalEdge);
    return builder.toString();
  }

  @Override
  public void render() {
    isReady("No canvas");
    // Can't render without output device
    if (out == null) {
      throw new IllegalArgumentException("No output stream!");
    }
    out.println(getElements());
  }

  private void isReady(String s) {
    if (border == null) {
      throw new IllegalArgumentException(s);
    }
  }

  public Border getBorder() {
    return border;
  }

  public char[][] getCanvasArray() {
    return canvasArray;
  }

  public char getPointGlyph() {
    return pointGlyph;
  }

  public void setPointGlyph(char glyph) {
    this.pointGlyph = glyph;
  }

}
