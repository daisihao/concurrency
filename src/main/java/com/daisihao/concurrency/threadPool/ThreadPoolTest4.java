package com.daisihao.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolTest4 {

    //使用schedule方法可以让线程延迟执行
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
//        for (int i= 0; i < 10; i++) {
//            final int index = i;
//            scheduledExecutorService.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    log.info("task{}",index);
//                }
//            },3, TimeUnit.SECONDS);
//        }
        //延迟1S后,每隔3S执行一次任务
//        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                log.info("task");
//            }
//        },1,3,TimeUnit.SECONDS);
        //scheduledExecutorService.shutdown();

        //使用定时器,定时执行任务
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        },new Date(),5*1000);
    }
}
