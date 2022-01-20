package com.imooc.springcloud;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigClientEurekaApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigClientEurekaApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
