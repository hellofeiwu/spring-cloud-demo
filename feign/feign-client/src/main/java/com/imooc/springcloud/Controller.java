package com.imooc.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Controller implements IService{

    @Value("${server.port}")
    private String port;

    public String test() {
        return "This is " + port;
    }

    public String test(@RequestParam(name = "timeout") int timeout) {
        while (--timeout >= 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("retry " + port);
        return "timeout is " + timeout;
    }

    public String sayHi() {
        int i = 10/0;
        return "hello world, port: " + port;
    }

    public String fail() {
        System.out.println("failed in feign-client");
        throw new RuntimeException("failed in feign-client");
    }
}
