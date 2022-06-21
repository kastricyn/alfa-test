package ru.kastricyn.alfatest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "gif", url = "${feign.gif-client.url}")
public interface GIFClient {
  @GetMapping(value = "/random")
  ResponseEntity<Map> getGIF(
      @RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);
}
