package com.imooc.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Controller implements IService{

    @Value("${server.port}")
    private String port;

    public String test() {
        return "This is " + port;
    }

    public String test(@RequestParam(name = "timeout") int timeout) {
        while (--timeout >= 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("retry " + port);
        return "timeout is " + timeout;
    }
}
