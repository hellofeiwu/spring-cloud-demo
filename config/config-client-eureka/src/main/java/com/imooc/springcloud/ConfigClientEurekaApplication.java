package com.imooc.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
// 这个不加都会被注册到注册中心去，因为配置文件中配置 配置中心 微服务 名字的时候
// 用到了 eureka server 的 discovery 这个功能
public class ConfigClientEurekaApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigClientEurekaApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
