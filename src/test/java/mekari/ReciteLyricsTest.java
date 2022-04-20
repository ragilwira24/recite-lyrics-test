package mekari;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ReciteLyricsTest {

  final PrintStream originalOut = System.out;
  final PrintStream originalErr = System.err;
  final ByteArrayOutputStream out = new ByteArrayOutputStream();
  final ByteArrayOutputStream err = new ByteArrayOutputStream();

  @BeforeEach // JUnit 5
  public void setUpStreams() {
    out.reset();
    err.reset();
    System.setOut(new PrintStream(out));
    System.setErr(new PrintStream(err));
  }

  @AfterEach // JUnit 5
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @ParameterizedTest
  @ValueSource(strings = {"0"})
  void checkValidationOfCountZeroThenReturnStringAsExpected(String value) {
    String[] args = value.split(" ");
    new CommandLine(new ReciteLyrics()).execute(args);
    assertEquals("Count lyrics must at least > 0", out.toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"13"})
  void checkValidationOfCountIfGreaterThanLyricsCountThenReturnStringAsExpected(String value) {
    String[] args = value.split(" ");
    new CommandLine(new ReciteLyrics()).execute(args);
    assertEquals("Count lyrics must at least <= 12", out.toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"12 -t NORMAL_AJA"})
  void checkValidationOfTypeIfTypeNotFoundThenReturnStringAsExpected(String value) {
    String[] args = value.split(" ");
    new CommandLine(new ReciteLyrics()).execute(args);
    assertEquals("Not found type to recite", out.toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"2 -t NORMAL_RECITER"})
  void usingNormalReciterThenReturnStringAsExcepted(String value) {
    String[] args = value.split(" ");
    new CommandLine(new ReciteLyrics()).execute(args);
    assertEquals("This is the malt that lay in the house that Jack built ", out.toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"2 -t RANDOM_RECITER"})
  void usingRandomReciterThenReturnStringAsExcepted(String value) {
    String[] args = value.split(" ");
    new CommandLine(new ReciteLyrics()).execute(args);
    assertNotNull(out.toString());
    assertNotEquals("", out.toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"2 -t SUBJECT_ONLY_RECITER"})
  void usingSubjectOnlyReciterThenReturnStringAsExcepted(String value) {
    String[] args = value.split(" ");
    new CommandLine(new ReciteLyrics()).execute(args);
    assertEquals("This is the malt the house ", out.toString());
  }

  @ParameterizedTest
  @ValueSource(strings = {"2 -t SUBJECT_ONLY_RANDOM_RECITER"})
  void usingSubjectOnlyRandomReciterThenReturnStringAsExcepted(String value) {
    String[] args = value.split(" ");
    new CommandLine(new ReciteLyrics()).execute(args);
    assertNotNull(out.toString());
    assertNotEquals("", out.toString());
  }




}
