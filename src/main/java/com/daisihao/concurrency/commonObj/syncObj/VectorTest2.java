package com.daisihao.concurrency.commonObj.syncObj;

import com.daisihao.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 这个方法验证了Vector是一个线程不安全你的类
 */
@Slf4j
@NotThreadSafe
public class VectorTest2 {

    public static Vector<Integer> vector = new Vector();

    public static void main(String[] args) {
        while (true){
            for (int i = 0; i<10;i++){
                vector.add(i);
            }

            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    for(int i = 0 ;i<vector.size();i++){
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    for(int i = 0 ;i<vector.size();i++){
                        vector.remove(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }

}
