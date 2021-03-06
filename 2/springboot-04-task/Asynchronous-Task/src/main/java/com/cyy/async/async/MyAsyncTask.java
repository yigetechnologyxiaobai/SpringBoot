package com.cyy.async.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyAsyncTask {

    @Async
    public void task1() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        System.out.println("任务一耗时：" + (end - start) + "毫秒");
    }

    @Async
    public void task2() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        System.out.println("任务二耗时：" + (end - start) + "毫秒");
    }

    public void task3() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();
        System.out.println("任务三耗时：" + (end - start) + "毫秒");
    }

}
