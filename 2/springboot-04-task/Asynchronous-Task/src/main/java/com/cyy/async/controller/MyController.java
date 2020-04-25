package com.cyy.async.controller;

import com.cyy.async.async.MyAsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    private MyAsyncTask asyncTask;

    @GetMapping("/test")
    public String test() throws InterruptedException {
        long start = System.currentTimeMillis();
        asyncTask.task1();
        asyncTask.task2();
        asyncTask.task3();
        long end = System.currentTimeMillis();
        System.out.println("全部任务完成，总耗时：" + (end - start) + "毫秒");
        return "ok";
    }

}
