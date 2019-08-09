package com.daisihao.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncAllTest {

    /**
     * 如果在静态方法static上使用synchronized修饰
     * 那么这个synchronized的作用域为:修饰的方法体
     * 作用对象：所有对象
     * 值得注意的是：如果有方法继承了这个类,改public方法上的修饰符synchronized不会被继承,如果子类有需要,那么需要在子类上自己加上synchronized修饰符
     */
    public synchronized static void print() {

        for (int i = 0; i < 10; i++) {
            log.info("i:{}", i);
        }
    }

    public static void main(String[] args) {
        SyncAllTest syncBlockTest1 = new SyncAllTest();
        SyncAllTest syncBlockTest2 = new SyncAllTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            syncBlockTest1.print();
        });
        executorService.execute(() -> {
            syncBlockTest2.print();
        });
        executorService.shutdown();
    }
}
