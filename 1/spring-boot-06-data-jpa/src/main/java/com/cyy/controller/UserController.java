package com.cyy.controller;

import com.cyy.pojo.User;
import com.cyy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //http://localhost:8080/saveUSer?username=lisi&password=1111
    @GetMapping("saveUser")
    public String saveUser(String username,String password){
        User user = new User(username, password);
        userRepository.save(user);
        return "保存"+user+"数据成功";
    }

    //http://localhost:8080/saveUSer?id=1&username=lisi&password=1111
    @GetMapping("updateUser")
    public String updateUser(Long id,String username,String password){
        User user = new User(id, username, password);
        userRepository.save(user);
        return "修改用户"+username+"成功";
    }

    //http://localhost:8080/deleteUserById/1
    @GetMapping("deleteUserById/{id}")
    public String deleteUserById(@PathVariable("id") Long id){

        userRepository.deleteById(id);
        return "删除用户ID="+id+"成功";
    }

    //http://localhost:8080/findUserAll
    @GetMapping("findUserAll")
    public List<User> findUserAll(){

        List<User> list = userRepository.findAll();
        list.forEach(user-> System.out.println(user));
        return list;
    }

    //http://localhost:8080/getUserById?id=2
    @GetMapping("getUserById/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id){

        Optional<User> user = userRepository.findById(id);

        return user;
    }

    //http://localhost:8080/findAllByUserName?username=zhangsan
    @GetMapping("findAllByUserName/{name}")
    public List<User> findAllByUserName(@PathVariable("name") String username){
        List<User> list = userRepository.findAllByUsername(username);
        //控制台输出所有用户
        list.forEach(user-> System.out.println(user));
        return list;
    }

}
