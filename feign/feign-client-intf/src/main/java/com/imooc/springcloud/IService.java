package com.imooc.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("feign-client")
public interface IService {
    @GetMapping("/test")
    public String test();

    @GetMapping("/delay")
    public String delay(@RequestParam("timeout") double timeout);

    @GetMapping("/sayHi")
    String sayHi();

    @GetMapping("/fail")
    String fail();
}
