package com.cyy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Calendar;

@Controller
@RequestMapping("/cyy")
public class MyController {

    @RequestMapping("test")
    public String test(Model model){
        model.addAttribute("msg","<h1>Hello World</h1>");
        model.addAttribute("users", Arrays.asList("张三","李四"));
        return "test";
    }

}
