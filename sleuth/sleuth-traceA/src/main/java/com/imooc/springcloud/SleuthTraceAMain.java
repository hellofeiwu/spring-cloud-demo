package com.imooc.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SleuthTraceAMain {
    private static final Logger log = LoggerFactory.getLogger(SleuthTraceAMain.class);

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("traceA")
    public String traceA(){
        log.info("-----------in traceA()");
        return restTemplate.getForEntity("http://sleuth-traceB/traceB", String.class).getBody();

    }

    public static void main(String[] args) {
        SpringApplication.run(SleuthTraceAMain.class, args);
    }
}
