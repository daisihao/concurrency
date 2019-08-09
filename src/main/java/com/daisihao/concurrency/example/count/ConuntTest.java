package com.daisihao.concurrency.example.count;

import com.daisihao.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 并发测试类
 */
@Slf4j
@NotThreadSafe
public class ConuntTest {

    //总请求数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //计数值
    public static int count = 0;

    //计数方法
    private static void add() {
        count++;
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
