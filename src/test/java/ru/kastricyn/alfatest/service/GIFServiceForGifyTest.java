package ru.kastricyn.alfatest.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GIFServiceForGifyTest {
    @Autowired
    private GIFService service;

    @Test
    void getGif() {
        var gif = service.getURLForGIF("RICH");
        Assertions.assertNotNull(gif);
    }
}
