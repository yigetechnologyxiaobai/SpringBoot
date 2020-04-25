package com.cyy.redis.mapper;

import com.cyy.redis.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from user")
    public List<User> queryAll();

    @Select("select * from user where id = #{id}")
    public User queryById(Integer id);

    @Update("update user set name=#{name},age=#{age},sex=#{sex} where id=#{id}")
    public void updateUser(User user);

    @Delete("delete from user where id = #{id}")
    public void deleteUser(Integer id);

    @Insert("insert into user (name,age,sex) values(#{name},#{age},#{sex})")
    public void insertUser();

    @Select("select * from user where name=#{name}")
    public User queryByName(String name);

}
