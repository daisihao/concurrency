package com.daisihao.concurrency.reentranlock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Slf4j
public class ReentranLockTest {

    //总线程数
    private static int threadNum = 5000;
    //同时并发数
    private static int conCurrentNum = 20;
    //自定义计数器
    private static int count = 0;

    //默认是一个不公平的锁
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(conCurrentNum);
        for (int i = 0; i < threadNum; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    count();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        log.info("count is {}", count);

    }

    private static void count() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }


}
