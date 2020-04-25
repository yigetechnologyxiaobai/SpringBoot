package com.cyy.repository;

import com.cyy.pojo.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//继承JpaRepository完成对数据库的操作
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where username = :username")
    List<User> findAllByUsername(@Param("username")String username);

}
