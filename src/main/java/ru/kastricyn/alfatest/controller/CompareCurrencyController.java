package ru.kastricyn.alfatest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.kastricyn.alfatest.service.CompareCurrencyService;
import ru.kastricyn.alfatest.service.GIFService;

@RestController()
public class CompareCurrencyController {
  private final GIFService gifService;
  private final CompareCurrencyService currencyService;

  @Autowired
  public CompareCurrencyController(GIFService gifService, CompareCurrencyService currencyService) {
    this.gifService = gifService;
    this.currencyService = currencyService;
  }

  @GetMapping
  public String changeCurrency() {
    return changeCurrency("RUB");
  }

  @GetMapping("{cur}")
  public String changeCurrency(@PathVariable("cur") String cur) {
    var tag = currencyService.getTagForGIF(currencyService.getPairValuesForCompare(cur));
    String url = gifService.getURLForGIF(tag.name());
    return "<img src = '" + url + "'></img><br>" + tag;
  }
}
