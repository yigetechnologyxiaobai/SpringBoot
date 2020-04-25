package com.cyy.service;

import com.cyy.pojo.User;
import org.springframework.stereotype.Service;

public interface UserService {

    public User queryByName(String name);

}
