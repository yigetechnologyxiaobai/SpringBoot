package com.cyy.springboot.mapper;

import com.cyy.springboot.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    public Account queryById(Integer id);

    public void insert(Account account);

}
