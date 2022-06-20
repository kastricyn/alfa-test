package ru.kastricyn.alfatest.client;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "gif", url = "${feign.gif-client.url}")
public interface GIFClient {
  @GetMapping(value = "random", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<JSONObject> getGIF(String api_key, String tag);
  // todo: добавить @RequestParam после эксперимента
}
