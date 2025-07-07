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

    public String delay(double timeout) {
        try {
            Thread.sleep((long)(timeout * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("in feign-client: timeout is " + timeout + " seconds");
        return "in feign-client: timeout is " + timeout + " seconds";
    }

    public String sayHi() {
        return "hello world, port: " + port;
    }

    public String fail() {
        System.out.println("failed in feign-client");
        throw new RuntimeException("failed in feign-client");
    }

    public String slowService(String id){
        try {
            Thread.sleep((long)(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("System print in feign-client: thread id " + id);
        return "in feign-client: thread id " + id;
    }
}
