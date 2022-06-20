package ru.kastricyn.alfatest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kastricyn.alfatest.model.ExchangeRates;

@FeignClient(name = "currency", url = "${feign.currency-client.url}")
public interface ExchangeRatesClient {

  @GetMapping(value = "latest.json")
  ResponseEntity<ExchangeRates> getLatestExchangeRate(@SpringQueryMap QueryParams queryParams);

  @GetMapping(value = "historical/{date}.json")
  ResponseEntity<ExchangeRates> getExchangeRateByDate(
      @PathVariable String date, @SpringQueryMap QueryParams queryParams);

  // возможно лучше переделать из рекорда в DTOObject/MAP, чтобы api сервиса не было связано с
  // именем полей в объектах
  // модели на Java (но здесь вроде всё рядом, пусть пока останется)
  record QueryParams(String app_id, String base, String symbols) {}
}
