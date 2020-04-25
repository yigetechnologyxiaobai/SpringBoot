package com.cyy.redis.controller;

import com.cyy.redis.pojo.User;
import com.cyy.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userAll")
    public List<User> queryAll(){
        return userService.queryAll();
    }

    @GetMapping("/user/{id}")
    public User queryUserById(@PathVariable("id") Integer id){
        return userService.queryUserById(id);
    }

    @GetMapping("/user")
    public void updateUser(User user){
        userService.updateUser(user);
    }

    @GetMapping("/deluser/{id}")
    public void delete(@PathVariable("id") Integer id){
        userService.deleteUser(id);
    }

}
