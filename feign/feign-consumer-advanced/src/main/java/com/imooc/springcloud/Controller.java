package com.imooc.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private IService iService;

    @GetMapping("/hello")
    public String hello() {
        return iService.test();
    }

    @PostMapping("/retry")
    public String retry(@RequestParam(name = "timeout") int timeout) {
        return iService.test(timeout);
    }
}
