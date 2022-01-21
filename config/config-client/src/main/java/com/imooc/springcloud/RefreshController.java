package com.imooc.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/refresh")
public class RefreshController {

    @Value("${word}")
    private String word;

    @Value("${food}")
    private String food;

    @GetMapping("/word")
    public String getWord() {
        return word;
    }

    @GetMapping("/food")
    public String getFood() {
        return food;
    }
}
