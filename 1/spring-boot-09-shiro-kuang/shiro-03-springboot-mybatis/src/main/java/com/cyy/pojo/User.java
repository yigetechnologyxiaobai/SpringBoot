package com.cyy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String perms;

    public User(String username, String password, String perms) {
        this.username = username;
        this.password = password;
        this.perms = perms;
    }
}
