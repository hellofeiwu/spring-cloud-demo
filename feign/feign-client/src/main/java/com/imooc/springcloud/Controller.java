package com.imooc.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller implements IService{

    @Value("${server.port}")
    private String port;

    public String test() {
        return "This is " + port;
    }
}
