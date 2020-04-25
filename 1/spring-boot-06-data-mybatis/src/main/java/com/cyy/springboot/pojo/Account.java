package com.cyy.springboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {
    private Integer id;
    private String name;
    private Float money;

    public Account(String name, Float money) {
        this.name = name;
        this.money = money;
    }
}
