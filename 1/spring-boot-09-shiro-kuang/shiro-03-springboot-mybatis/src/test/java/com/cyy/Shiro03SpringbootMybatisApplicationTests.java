package com.cyy;

import com.cyy.pojo.User;
import com.cyy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Shiro03SpringbootMybatisApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        User user = userService.queryByName("root");
        System.out.println(user);
    }

}
