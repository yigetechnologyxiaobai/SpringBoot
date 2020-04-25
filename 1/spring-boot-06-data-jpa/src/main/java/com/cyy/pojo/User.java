package com.cyy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//配置JPA注解配置映射关系
@Data
@Entity                     //告诉JPA这个一个实体类（和数据表映射的类）
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")     //@Table来指定和哪个数据表对应，如果省略默认表名就是首字母小写的类名
public class User {

    @Id         //主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //自增主键
    private Long id;

    @Column(name = "last_name", nullable = false, unique = true)    //这是和数据表对应的列，不为空且唯一
    private String username;

    @Column                     //省略则默认列名就是属性名
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
