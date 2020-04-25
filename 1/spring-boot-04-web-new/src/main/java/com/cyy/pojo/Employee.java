package com.cyy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class Employee implements Serializable {
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;     //0 女   1 男

    private Department department;
    private Date birth;

    public Employee(Integer id, String lastName, String email, Integer gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = birth;
        this.birth = new Date();
    }
}
