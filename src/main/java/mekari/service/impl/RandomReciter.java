package mekari.service.impl;

import mekari.service.BaseReciter;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomReciter extends BaseReciter {

  protected final Random random = new Random();
  protected final List<String> arrayList = getListOfLyrics();

  @Override
  public void recite(int count) {
    String results = IntStream.range(0, count).mapToObj(index -> {
      int randomIndex = getRandomInteger();
      return getValueLyrics(randomIndex);
    }).collect(Collectors.joining(" "));
    System.out.printf("This is %s", results);
  }

  @Override
  public String getValueLyrics(int lineArray){
    return arrayList.get(lineArray);
  }

  public int getRandomInteger(){
    return (random.nextInt(countOfLyrics() + 1 - 1) + 1) - 1;
  }

}
