package com.cyy.timing.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
public class StaticScheduleTask {

    //添加定时任务
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate = 5000)
    @Scheduled(cron = "0/3 * * * * *")
    private void configureTasks() {
        System.out.println("执行静态定时任务时间：" + LocalDateTime.now());
    }

}
