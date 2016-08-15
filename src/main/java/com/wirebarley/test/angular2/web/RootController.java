package com.wirebarley.test.angular2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by jake on 2016. 8. 14..
 */
@Controller
public class RootController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
