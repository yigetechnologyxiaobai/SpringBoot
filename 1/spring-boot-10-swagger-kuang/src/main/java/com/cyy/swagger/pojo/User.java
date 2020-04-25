package com.cyy.swagger.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

//@Api(注解)
@ApiModel("用户实体类")
public class User implements Serializable {

    @ApiModelProperty("用户名")
    public String username;
    @ApiModelProperty("密码")
    public String password;

}
