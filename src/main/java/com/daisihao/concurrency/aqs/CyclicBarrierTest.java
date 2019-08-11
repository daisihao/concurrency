package com.daisihao.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierTest {

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
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int i) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        log.info("{} is ready",i);
        cyclicBarrier.await();
        log.info("{} continue" ,i);
    }


}
