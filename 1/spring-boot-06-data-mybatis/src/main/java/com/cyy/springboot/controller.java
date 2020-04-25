package com.cyy.springboot;

import com.alibaba.fastjson.JSON;
import com.cyy.springboot.mapper.UserMapper;
import com.cyy.springboot.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @Autowired
    private UserMapper mapper;

    @GetMapping("queryById/{id}")
    public String queryById(@PathVariable("id") Integer id){
        Account account = mapper.queryById(id);
        Object json = JSON.toJSON(account);
        return json.toString();
    }

}
