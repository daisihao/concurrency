package com.daisihao.concurrency.example.Atomic;

import com.daisihao.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;


@Slf4j
@ThreadSafe
public class AtomicReferenceTest {

    private static AtomicReference<Integer> count = new AtomicReference(0);

    public static void main(String[] args) {
        count.compareAndSet(0,2); //yes
        count.compareAndSet(0,1); //no
        count.compareAndSet(1,3); //no
        count.compareAndSet(2,4); //yes
        log.info("count:{}",count);
    }
}
