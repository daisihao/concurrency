package com.daisihao.concurrency.commonObj.unSafeObj;

import com.daisihao.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
/**
 * 为什么要提供StringBuilder这个类呢？因为StringBuffer使用了synchronized关键字修饰，在多线程情况下效率比较低，
 * 如果在方法的局部变量中，因为有堆栈封闭，所以使用StringBuilder的效率要高一点
 */
public class StringBuilderTest {

    //总请求数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //这里的成员变量必须new出来
    public static StringBuilder stringBuilder = new StringBuilder();

    //
    private static void update() {
        stringBuilder.append(1);
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
                    update();
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
        log.info("StringBuilder的长度为：{}", stringBuilder.length());
    }
}
