package com.daisihao.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncOnlyTest {

    /**
     * 在代码块中使用synchronized
     * 作用域：用{}包裹的代码
     * 作用对象：调用改代码块的对象
     */
    public void print1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("i:{}", i);
            }
        }
    }


    /**
     * 在方法上使用synchronized可以保证同一个对象，同一时刻，只有一个线程可以访问
     * 作用域：整个方法
     * 作用对象：调用该方法的对象
     */
    public synchronized void print2() {

        for (int i = 0; i < 10; i++) {
            log.info("i:{}", i);
        }
    }

    public static void main(String[] args) {
        SyncOnlyTest syncBlockTest1 = new SyncOnlyTest();
        SyncOnlyTest syncBlockTest2 = new SyncOnlyTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncBlockTest1.print1();
        });
        executorService.execute(() -> {
            syncBlockTest2.print1();
        });
        executorService.shutdown();
    }
}