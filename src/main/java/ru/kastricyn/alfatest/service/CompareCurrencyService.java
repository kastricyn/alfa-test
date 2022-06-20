package ru.kastricyn.alfatest.service;

import org.springframework.lang.NonNull;
import ru.kastricyn.alfatest.model.PairForCompare;
import ru.kastricyn.alfatest.model.TagsForGIF;

public interface CompareCurrencyService {
  PairForCompare getPairValuesForCompare(@NonNull String symbols);

  TagsForGIF getTagForGIF(PairForCompare pairForCompare);
}
