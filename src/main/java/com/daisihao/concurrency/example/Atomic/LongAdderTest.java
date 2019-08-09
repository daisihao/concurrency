package com.daisihao.concurrency.example.Atomic;

import com.daisihao.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class LongAdderTest {

    //总请求数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //计数值
    //JDK8新增方法,在高并发会分散压力，性能好一点
    //缺点是在并发的情况下,可能会出现计数错误,如果要生成全局唯一流水号,建议还是使用AtomicLong
    public static LongAdder count = new LongAdder();

    //计数方法
    private static void add() {
        count.increment();
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
