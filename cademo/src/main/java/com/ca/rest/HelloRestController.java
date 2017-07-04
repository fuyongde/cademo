package com.ca.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuyongde on 2017/6/30.
 */
@RestController
public class HelloRestController {

    @GetMapping(value = "/index")
    public Map<String, Object> index() {
        Map<String, Object> result = new HashMap<>();
        result.put("date", new Date());
        result.put("index", "hello");
        return result;
    }
}
