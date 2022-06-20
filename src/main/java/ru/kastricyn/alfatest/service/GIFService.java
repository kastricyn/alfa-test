package ru.kastricyn.alfatest.service;

import org.springframework.boot.configurationprocessor.json.JSONObject;

public interface GIFService {
  JSONObject getGIFAsJSON(String tag);

  String getURLForGIF(JSONObject gif);
}
