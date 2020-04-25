package com.cyy.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * zookeeper：服务注册与发现
 *
 * @Author: cyy
 * @Date: 2020/4/22  11:44
 */
@Service //可以被扫描到，在项目已启动就自动注册到注册中心
@Component      //使用Dubbo后尽量不要用Service注解
public class TicketServiceImpl implements TicketService {
    @Override
    public String sellTicket() {
        return "NBA门票";
    }
}
