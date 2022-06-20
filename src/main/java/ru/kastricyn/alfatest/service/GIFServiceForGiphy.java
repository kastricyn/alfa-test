package ru.kastricyn.alfatest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.kastricyn.alfatest.client.GIFClient;

import java.util.Map;
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

  /**
   * Полностью завязан на представлении <a
   * href="https://developers.giphy.com/docs/api/schema#gif-object">GIF Object от giphy.com</a>
   */
  @Override
  public String getURLForGIF(@NonNull String tag) {

    var json = client.getGIF(apiKey, tag);

    return (String)
        ((Map)
                ((Map) ((Map) Objects.requireNonNull(json.getBody()).get("data")).get("images"))
                    .get("original"))
            .get("url");
    //todo: record of model GIF object
  }
}
