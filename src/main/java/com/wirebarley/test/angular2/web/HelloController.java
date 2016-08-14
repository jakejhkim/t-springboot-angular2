package com.wirebarley.test.angular2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jake on 2016. 8. 14..
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world";
    }
}
