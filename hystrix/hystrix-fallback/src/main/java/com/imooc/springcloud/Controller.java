package com.imooc.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private MyService myService;

    @GetMapping("/hello")
    public String hello(){
        return "from hystrix-fallback: " + myService.sayHi();
    }

    @GetMapping("/handleFail")
    public String handleFail() {
        return "from hystrix-fallback: " + myService.fail();
    }
}
