package com.daisihao.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreTest {

    private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        //值容许5个并发
        final Semaphore semaphore = new Semaphore(5);

        for(int i=0;i<threadCount;i++){
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire(); //获得许可
                    test(threadNum);
                    semaphore.release(); //释放许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        Thread.sleep(5000);
    }
}
