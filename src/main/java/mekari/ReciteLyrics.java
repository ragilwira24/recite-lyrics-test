package mekari;

import io.vavr.control.Try;
import mekari.constants.ReciterType;
import mekari.service.BaseReciter;
import mekari.service.impl.NormalReciter;
import mekari.service.impl.RandomReciter;
import mekari.service.impl.SubjectOnlyRandomReciter;
import mekari.service.impl.SubjectOnlyReciter;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "checksum", mixinStandardHelpOptions = true, version = "checksum 4.0",
    description = "Prints the checksum (SHA-256 by default) of a file to STDOUT.")
public class ReciteLyrics implements Callable<Integer> {

  @CommandLine.Parameters(index = "0", description = "Count of lyrics you want to recite")
  private Integer count;

  @CommandLine.Option(names = {"-t", "--type"}, description = "NORMAL_RECITER, RANDOM_RECITER, SUBJECT_ONLY_RECITER, SUBJECT_ONLY_RANDOM_RECITER",
      defaultValue = "NORMAL_RECITER")
  private String type;

  @Override
  public Integer call() throws Exception {
    return Try.of(() -> {
      validateArguments();
      BaseReciter baseReciter;

      if (ReciterType.NORMAL_RECITER.name().equals(type)){
        baseReciter = new NormalReciter();
      } else if (ReciterType.RANDOM_RECITER.name().equals(type)){
        baseReciter = new RandomReciter();
      } else if (ReciterType.SUBJECT_ONLY_RECITER.name().equals(type)){
        baseReciter = new SubjectOnlyReciter();
      } else if (ReciterType.SUBJECT_ONLY_RANDOM_RECITER.name().equals(type)){
        baseReciter = new SubjectOnlyRandomReciter();
      } else {
        throw new Exception("Not found type to recite");
      }

      baseReciter.recite(count);
      return 0;
    }).onFailure(e -> System.out.print(e.getMessage())).getOrElse(500);
  }

  private void validateArguments() throws Exception{
    if (count == 0) {
      throw new Exception("Count lyrics must at least > 0");
    }

    int countOfLyrics = BaseReciter.countOfLyrics();
    if (count > BaseReciter.countOfLyrics()){
      throw new Exception("Count lyrics must at least <= " + countOfLyrics);
    }

  }

  public static void main(String... args) {
    int exitCode = new CommandLine(new ReciteLyrics()).execute(args);
    System.exit(exitCode);
  }
}
