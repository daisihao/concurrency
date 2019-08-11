package com.daisihao.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierTest2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            final int thread = i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    race(thread);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            });
        }
        log.info("finish");
        executorService.shutdown();
    }

    private static void race(int i) throws InterruptedException{
        Thread.sleep(1000);
        log.info("{} is ready",i);
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.warn("error InterruptedException");
        } catch (BrokenBarrierException e) {
            log.warn("error BrokenBarrierException");
        } catch (TimeoutException e) {
            log.warn("error TimeoutException");
        }
        log.info("{} continue" ,i);
    }


}
