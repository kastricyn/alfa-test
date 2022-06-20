package ru.kastricyn.alfatest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompareCurrencyController {

  @GetMapping(value = "{cur}")
  public String changeCurrency(@PathVariable("cur") String cur) {
    String url = "https://media4.giphy.com/media/SHyKxIVTdIOvNa4yh6/giphy.gif";
    return "<img src = '" + url + "'></img>";
  }
}
