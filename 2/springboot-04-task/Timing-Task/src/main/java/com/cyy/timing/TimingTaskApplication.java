package com.cyy.timing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling       //开启定时任务
@EnableAsync            //开启多线程
@SpringBootApplication
public class TimingTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimingTaskApplication.class, args);
    }

}
