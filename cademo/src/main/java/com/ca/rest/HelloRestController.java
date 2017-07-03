package com.ca.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by fuyongde on 2017/6/30.
 */
@RestController
public class HelloRestController {

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }
}
