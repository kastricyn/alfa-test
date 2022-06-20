package ru.kastricyn.alfatest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kastricyn.alfatest.model.ExchangeRates;

@FeignClient(name = "currency", url = "https://openexchangerates.org/api/")
public interface ExchangeRatesClient {

  @GetMapping(value = "latest.json")
  ExchangeRates getLatestExchangeRate(@SpringQueryMap QueryParams queryParams);

  @GetMapping(value = "{date}.json")
  ExchangeRates getExchangeRateByDate(
      @PathVariable String date, @SpringQueryMap QueryParams queryParams);

  record QueryParams(String app_id, String base, String symbols) {}
}
