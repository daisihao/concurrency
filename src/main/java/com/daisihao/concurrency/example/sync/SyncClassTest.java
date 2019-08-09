package com.daisihao.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


/**
 *
 * 使用synchronized修饰类,
 * 该修饰符的作用范围是该类
 * 作用对象:所有类
 * 这里使用两种方式修改bug
 * 一、将add方法改为synchronized static
 * 二、使用synchronized修饰类
 */
@Slf4j
public class SyncClassTest {

    //总请求数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //计数值
    public static int count = 0;

    //计数方法
    private static void add() {
        synchronized (SyncClassTest.class){
            count++;
        }
    }

    public static void main(String[] args) throws Exception {
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量,容许并发数
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    //判断当前线程是否容许被执行
                    semaphore.acquire();
                    add();
                    //释放当前进程
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        //保证countDown为0,需要捕捉异常
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        log.info("count:{}", count);
    }

}
