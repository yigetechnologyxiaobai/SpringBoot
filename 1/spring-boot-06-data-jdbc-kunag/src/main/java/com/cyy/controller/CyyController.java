package com.cyy.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class CyyController {

    @Autowired
    JdbcTemplate template;

    @GetMapping("/queryAll")
    public Object queryAll() {
        String sql = "select * from user";
        List<Map<String, Object>> maps = template.queryForList(sql);
        Object json = JSON.toJSON(maps);
        return json;
    }

    @GetMapping("/add")
    public void add(){
        String sql = "insert into user(username,password) values(?,?)";
        Object[] obj = new Object[2];
        obj[0] = "库里";
        obj[1] = "555";
        template.update(sql,obj);
    }

    @GetMapping("/update")
    public void update(){
        String sql = "update user set username = ?,password = ? where id = ?";
        Object[] obj = new Object[3];
        obj[0] = "詹姆斯";
        obj[1] = "666";
        obj[2] = 1;
        template.update(sql,obj);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id){
        String sql = "delete from user where id = ?";
        template.update(sql,id);
    }

}
