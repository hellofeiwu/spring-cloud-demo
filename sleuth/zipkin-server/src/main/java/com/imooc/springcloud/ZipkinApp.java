package com.imooc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinApp {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApp.class, args);
    }
}
