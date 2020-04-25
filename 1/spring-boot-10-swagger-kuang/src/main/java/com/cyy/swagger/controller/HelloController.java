package com.cyy.swagger.controller;

import com.cyy.swagger.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    //Operation接口，不是放在类上的，是方法上的
    @ApiOperation("hello2控制类")
    @GetMapping("/hello2")
    public String hello2(@ApiParam("用户名") String username) {
        return "hello" + username;
    }

    //只要我们的接口中，返回值中存在实体类，它就会被扫描到 Swagger 中
    @PostMapping("/user")
    public User user() {
        return new User();
    }

}
