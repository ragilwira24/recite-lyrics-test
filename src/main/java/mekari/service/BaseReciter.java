package mekari.service;

import java.util.Arrays;
import java.util.List;

public abstract class BaseReciter {

  protected static final String[] listOfLyrics = new String[]{
      "the horse and the hound and the horn that belonged to",
      "the farmer sowing his corn that kept",
      "the rooster that crowed in the morn that woke",
      "the priest all shaven and shorn that married",
      "the man all tattered and torn that kissed",
      "the maiden all forlorn that milked",
      "the cow with the crumpled horn that tossed",
      "the dog that worried",
      "the cat that killed",
      "the rat that ate",
      "the malt that lay in",
      "the house that Jack built"
  };

  public static int countOfLyrics(){
    return listOfLyrics.length;
  }

  public List<String> getListOfLyrics() {
    return Arrays.asList(listOfLyrics);
  }

  public abstract void recite(int count);
  public abstract String getValueLyrics(int lineArray);

}
