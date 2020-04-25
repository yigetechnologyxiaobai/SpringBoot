package com.atguigu.springboot.controller;

import com.atguigu.springboot.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    //浏览器客户端返回的都是json数据
    /*@ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object> handleException(Exception e){

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code","user.notexit");
        map.put("message",e.getMessage());
        return map;

    }*/

    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){

        Map<String,Object> map = new HashMap<String, Object>();
        //传入我们自己的错误状态码  4xx  5xx,否则就不会进入订制错误页面的解析流程
        //Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexit");
        map.put("message","用户出错");
        request.setAttribute("ext",map);
        return "forward:/error";

    }

}
