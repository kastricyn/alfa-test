package ru.kastricyn.alfatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.kastricyn.alfatest.client.GIFClient;

import java.util.Objects;

@Service
public class GIFServiceForGiphy implements GIFService {
  private final GIFClient client;
  @Value("${feign.gif-client.api-key}")
  private String apiKey;

  @Autowired
  public GIFServiceForGiphy(GIFClient client) {
    this.client = client;
  }

  @Override
  public JSONObject getGIFAsJSON(String tag) {
    return Objects.requireNonNull(client.getGIF(apiKey, tag).getBody());
  }

  /**
   * Полностью завязан на представлении <a
   * href="https://developers.giphy.com/docs/api/schema#gif-object">GIF Object от giphy.com</a>
   */
  @Override
  public String getURLForGIF(@NonNull JSONObject gif) {
    try {
      return (String) gif.getJSONObject("images").getJSONObject("original").get("url");
    } catch (JSONException e) {
      throw new RuntimeException(e);
    }
  }
}
