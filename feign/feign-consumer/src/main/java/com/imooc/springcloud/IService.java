package com.imooc.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-client")
public interface IService {
    @GetMapping("/test") // 这个是eureka-client中的接口路径
    public String hello();
}
