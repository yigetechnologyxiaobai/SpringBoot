package com.atguigu.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@ResponseBody注解使这个类的所有方法返回的数据直接写给浏览器，(如果是对象转为json数据)
/*@ResponseBody
@Controller*/
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello world quick!";
    }

    //RESTAP的方式
}
