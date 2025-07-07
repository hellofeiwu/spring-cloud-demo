package com.imooc.springcloud.hystrix;

import com.imooc.springcloud.MyService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

@Component
public class Fallback implements MyService {
    @Override
    public String test() {
        return null;
    }

    @Override
    public String delay(double timeout) {
        System.out.println("in hystrix-fallback: handle timeout " + timeout + " seconds");
        return "in hystrix-fallback: handle timeout " + timeout + " seconds";
    }

    @Override
    public String sayHi() {
        System.out.println("Fallback: handle fail");
        return "Fallback: handle fail";
    }

    @Override
    public String fail() {
        System.out.println("Fallback: handle fail");
        return "Fallback: handle fail";
    }

    @Override
    public String slowService(String id){
        System.out.println("Fallback response for thread id " + id + " (too many concurrent requests)");
        return "Fallback response for thread id " + id + " (too many concurrent requests)";
    }
}
