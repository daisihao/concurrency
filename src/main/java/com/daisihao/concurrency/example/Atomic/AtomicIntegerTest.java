package com.daisihao.concurrency.example.Atomic;

import com.daisihao.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发测试类
 */
@Slf4j
@ThreadSafe
public class AtomicIntegerTest {

    //总请求数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //计数值
    //底层使用Native方法，循环比对值是否一致，CAS(Compaer And Swap)
    public static AtomicInteger count = new AtomicInteger(0);

    //计数方法
    private static void add() {
        count.incrementAndGet();
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
