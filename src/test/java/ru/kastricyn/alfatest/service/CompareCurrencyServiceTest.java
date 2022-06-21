package ru.kastricyn.alfatest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CompareCurrencyServiceTest {
  @Autowired
  private CompareCurrencyService service;

  @Test
  void getExchange() {
    Assertions.assertNotNull(service.getPairValuesForCompare("RUB"));
  }
}
