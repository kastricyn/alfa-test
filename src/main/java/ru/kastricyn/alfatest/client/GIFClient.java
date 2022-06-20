package ru.kastricyn.alfatest.client;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "gif", url = "https://api.giphy.com/v1/gifs/random")
public interface GIFClient {
  @GetMapping(value = "", produces = MediaType.IMAGE_JPEG_VALUE)
  ResponseParameters getGIF(String api_key, @SpringQueryMap RequireParameters requireParameters);

  record RequireParameters(String api_key, String start, String end, String base, String symbols) {}

  record ResponseParameters(String start_date, String end_date, String base, JSONObject rates) {}
}
