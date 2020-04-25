package com.cyy.springboot.controller;

import com.cyy.springboot.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    @Autowired
    private AsyncService service;

    @GetMapping("/hello")
    public String hello() {
        service.hello();
        return "ok";
    }

}
