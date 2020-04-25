package com.cyy.service;

import com.cyy.mapper.UserMapper;
import com.cyy.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryByName(String name) {
        return userMapper.queryByName(name);
    }
}
