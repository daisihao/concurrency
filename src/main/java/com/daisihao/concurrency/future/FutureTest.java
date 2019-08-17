package com.daisihao.concurrency.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTest {

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            Thread.sleep(5000);
            return "OK";
        }
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //使用future接收线程任务的计算结果
        Future<String> future = executorService.submit(new MyCallable());
        log.info("do something in main");
        Thread.sleep(1000);
        String s = future.get();
        log.info("result {}",s);
    }
}
