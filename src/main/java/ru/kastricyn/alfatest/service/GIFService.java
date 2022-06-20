package ru.kastricyn.alfatest.service;

import org.springframework.boot.configurationprocessor.json.JSONObject;

public interface GIFService {
  String getURLForGIF(String tag);
}
