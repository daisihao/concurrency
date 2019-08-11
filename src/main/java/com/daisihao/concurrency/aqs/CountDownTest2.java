package com.daisihao.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownTest2 {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++){
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        //指定等待时间和单位
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        //并不会直接关闭线程池,会等待当前已经创建的线程结束后才关闭线程池
        executorService.shutdown();
    }

    private static void test(int i) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}",i);
    }
}
