package mekari.service.impl;

public class SubjectOnlyReciter extends NormalReciter {

  @Override
  public String getValueLyrics(int lineArray) {
    String parentValue = super.getValueLyrics(lineArray);
    String[] splitTheLyrics = parentValue.split(" ");
    return splitTheLyrics[0] + " " + splitTheLyrics[1];
  }
}
