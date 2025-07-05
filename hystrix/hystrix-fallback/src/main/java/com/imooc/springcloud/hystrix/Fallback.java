package com.imooc.springcloud.hystrix;

import com.imooc.springcloud.MyService;
import org.springframework.stereotype.Component;

@Component
public class Fallback implements MyService {
    @Override
    public String test() {
        return null;
    }

    @Override
    public String test(int timeout) {
        return null;
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
}
