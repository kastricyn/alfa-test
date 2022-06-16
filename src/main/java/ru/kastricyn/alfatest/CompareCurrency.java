package ru.kastricyn.alfatest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompareCurrency {

    @GetMapping("{cur}")
    public String changeCurrency(@PathVariable String cur) {
        return "It's all the best!:)\n" + cur;
    }
}

