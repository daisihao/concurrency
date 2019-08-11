package com.daisihao.concurrency.commonObj.syncObj;

import com.daisihao.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * HashTable是一个线程安全的类
 */
@Slf4j
@ThreadSafe
public class HashTableTest {

    //总请求数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //这里的成员变量必须new出来
    public static Map<Integer,Integer> map = new Hashtable<>();

    //
    private static void update(int i) {
        map.put(i,i);
    }

    public static void main(String[] args) throws Exception {
        //定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量,容许并发数
        final Semaphore semaphore = new Semaphore(threadTotal);
        //计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++) {
            int j = i;
            executorService.execute(() -> {
                try {
                    //判断当前线程是否容许被执行
                    semaphore.acquire();
                    update(j);
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
        log.info("map的长度为：{}", map.size());
    }
}
