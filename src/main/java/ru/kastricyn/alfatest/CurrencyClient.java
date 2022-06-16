package ru.kastricyn.alfatest;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "currency", url = "https://openexchangerates.org/api/")
public interface CurrencyClient {

  @GetMapping("time-series.json")
  ResponseParameters compare(@SpringQueryMap RequireParameters parameters);

  record RequireParameters(String appId, String start, String end, String base, String symbols) {}

  record ResponseParameters(String start_date, String end_date, String base, JSONObject rates) {}
}
