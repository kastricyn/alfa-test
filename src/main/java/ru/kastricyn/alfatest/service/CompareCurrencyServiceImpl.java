package ru.kastricyn.alfatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.kastricyn.alfatest.client.ExchangeRatesClient;
import ru.kastricyn.alfatest.model.PairForCompare;
import ru.kastricyn.alfatest.model.TagsForGIF;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CompareCurrencyServiceImpl implements CompareCurrencyService {
  /**
   * Also we can create instances of {@link ExchangeRatesClient} for yesterday and today for all
   * symbols and send request to {@link ExchangeRatesClient} only for today and if today we didn't
   * send about yesterday.
   */
  private final ExchangeRatesClient client;

  @Value("${feign.currency-client.app-id}")
  private String app_id;

  @Value("${feign.currency-client.base-currency}")
  private String baseCurrency;

  @Autowired
  public CompareCurrencyServiceImpl(ExchangeRatesClient client) {
    this.client = client;
  }

  @Override
  public PairForCompare getPairValuesForCompare(@NonNull String symbols) {
    var queryVars = new ExchangeRatesClient.QueryParams(app_id, baseCurrency, symbols);
    var todayValues = client.getLatestExchangeRate(queryVars);

    var yesterdayDate =
        LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    var yesterdayValues = client.getExchangeRateByDate(yesterdayDate, queryVars);

    String symbol = symbols.toUpperCase().split(",", 2)[0];
    // todo: check if symbols incorrect

    return new PairForCompare(yesterdayValues.rates().get(symbol), todayValues.rates().get(symbol));
  }

  //todo: tests
  @Override
  public TagsForGIF getTagForGIF(PairForCompare pairForCompare) {
    return pairForCompare.yesterday() > pairForCompare.today()
        ? TagsForGIF.BROKE
        : pairForCompare.yesterday() < pairForCompare.today() ? TagsForGIF.RICH : TagsForGIF.INTACT;
  }
}
