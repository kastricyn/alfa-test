package ru.kastricyn.alfatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kastricyn.alfatest.controller.CompareCurrencyController;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AlfaTestApplicationTests {
  @Autowired private CompareCurrencyController controller;

  @Test
  void contextLoads() {
    assertNotNull(controller);
  }
}
