package com.imooc.springcloud;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private MyService myService;

    @GetMapping("/hello")
    public String hello(){
        return "from hystrix-fallback: " + myService.sayHi();
    }

    @GetMapping("/handleFail")
    public String handleFail() {
        return "from hystrix-fallback: " + myService.fail();
    }

    @GetMapping("/timeout")
    public String handleTimeout(@RequestParam("timeout") double timeout) {
        return "from hystrix-fallback: "+ myService.delay(timeout);
    }

    // 配置线程池隔离
    @HystrixCommand(
            commandKey = "slowServiceCommand", // 这个是这个方法的唯一id
            threadPoolKey = "slowServiceThreadPool", // 提供给这个方法的线程池的名字
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"), // 允许有几个队列
                    @HystrixProperty(name = "maxQueueSize", value = "2"), // 一个队列中可以有几个人
                    // 所以可以同时响应的请求数就是 coreSize * maxQueueSize
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "2") // 优先级更高的一个队列中可以有几个人
            },
            fallbackMethod = "fallback2"
    )
    @GetMapping("/testSlow/{id}")
    public String testSlowService(@PathVariable("id") String id) {
        return "from hystrix-consumer: "+ myService.slowService(id);
    }

    public String fallback2(String id){
        System.out.println("System print Fallback2 for thread id " + id);
        return "Fallback2 for thread id " + id;
    }
}
