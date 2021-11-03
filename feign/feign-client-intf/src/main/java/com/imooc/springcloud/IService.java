package com.imooc.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("feign-client")
public interface IService {
    @GetMapping("/test")
    public String test();
}
