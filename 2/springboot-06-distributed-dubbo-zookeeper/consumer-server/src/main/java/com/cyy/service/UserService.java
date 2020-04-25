package com.cyy.service;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Author: cyy
 * @Date: 2020/4/22  21:54
 */
@Service
public class UserService {

    /**
     * 想拿到 provider-service 提供的票，要去注册中心拿到服务
     * <p>
     * 引用， Pom坐标，可以定义路径相同的接口名
     */
    @Reference
    TicketService ticketService;

    public void sellTicket() {
        String ticket = ticketService.sellTicket();
        System.out.println("在注册中心拿到：" + ticket);
    }

}
