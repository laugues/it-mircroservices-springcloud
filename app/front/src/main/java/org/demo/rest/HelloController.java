package org.demo.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HelloController {

    @RequestMapping("/")
    @HystrixCommand
    public String index() {
        return "Hello !";
    }

}