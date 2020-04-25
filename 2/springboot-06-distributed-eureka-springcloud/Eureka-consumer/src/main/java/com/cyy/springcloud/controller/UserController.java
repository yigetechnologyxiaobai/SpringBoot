package com.cyy.springcloud.controller;

import com.cyy.springcloud.SpringcloudApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: cyy
 * @Date: 2020/4/24  11:45
 */
@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate = SpringcloudApplication.restTemplate();

    @GetMapping("/buy/{name}")
    public String buyTicket(@PathVariable("name") String name) {
        String s = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return name + "购买了" + s;
    }

}
