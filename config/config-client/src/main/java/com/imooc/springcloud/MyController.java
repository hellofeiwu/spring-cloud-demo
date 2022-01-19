package com.imooc.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Value("${name}")
    private String name;

    @Value("${myWord}")
    private String word;

    @GetMapping("/name")
    public String getName() {
        return "name: " + name;
    }

    @GetMapping("/word")
    public String getWord() {
        return "word: " + word;
    }
}
