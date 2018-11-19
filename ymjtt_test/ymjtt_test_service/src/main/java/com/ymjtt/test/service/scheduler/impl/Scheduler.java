package com.ymjtt.test.service.scheduler.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @auther ywx
 * @date 2018/11/15 14:15
 * @info
 **/
@Service
public class Scheduler {

    @Scheduled(cron = "0/3 * * * * ?")  //每隔5秒执行一次定时任务
    public void consoleInfo(){
        System.out.println("定时任务");
    }
}
