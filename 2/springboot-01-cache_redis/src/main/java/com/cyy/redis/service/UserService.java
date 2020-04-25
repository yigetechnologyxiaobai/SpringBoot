package com.cyy.redis.service;

import com.cyy.redis.mapper.UserMapper;
import com.cyy.redis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@CacheConfig(cacheNames = "emp")    //类上配置之后，方法上不用配置
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(cacheNames = "users")
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Cacheable(cacheNames = "user", keyGenerator = "myKeyGenerator",
            condition = "#id>0", unless = "#id==3")
    public User queryUserById(Integer id) {
        return userMapper.queryById(id);
    }

    @CachePut(value = "user", key = "user.id")
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @CacheEvict(value = "user", key = "#id", beforeInvocation = true)
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Caching(
            cacheable = {
                    @Cacheable(value = "user", key = "#name")
            },
            put = {
                    @CachePut(value = "user", key = "#result.id"),
                    @CachePut(value = "user", key = "#result.name")
            }
    )
    public User queryUserByName(String name) {
        return userMapper.queryByName(name);
    }

}
