package com.cyy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class SpringBoot04JdbcApplicationTests {

    @Autowired
    DataSource dataSource;



    @Test
    void contextLoads() throws SQLException {

        //查看一下默认的数据源混： com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());

        //获取数据源
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        // xxxx Template：SpringBoot已经配置好了模板bean，拿来即用

        //关闭数据源
        connection.close();

    }

}
