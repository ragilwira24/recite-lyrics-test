package mekari.service.impl;

import mekari.service.BaseReciter;

import java.util.List;

public class NormalReciter extends BaseReciter {

  protected final List<String> arrayList = getListOfLyrics();

  @Override
  public void recite(int count) {
    StringBuilder lyricsResult = new StringBuilder();
    for (int i = count; i > 0; i--) {
      int lineArray = (arrayList.size() - i);
      lyricsResult.append(getValueLyrics(lineArray)).append(" ");
    }
    System.out.printf("This is %s", lyricsResult);
  }

  @Override
  public String getValueLyrics(int lineArray){
    return arrayList.get(lineArray);
  }

}
