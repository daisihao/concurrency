package com.daisihao.concurrency.example.Atomic;

import com.daisihao.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


@Slf4j
@ThreadSafe
public class AtomicReferenceFiledUpdateTest {

    private static AtomicIntegerFieldUpdater<AtomicReferenceFiledUpdateTest> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceFiledUpdateTest.class, "count");

    @Getter
    private volatile int count = 100;


    public static void main(String[] args) {

        AtomicReferenceFiledUpdateTest atomicReferenceFiledUpdateTest = new AtomicReferenceFiledUpdateTest();

        if (updater.compareAndSet(atomicReferenceFiledUpdateTest, 100, 120)) {
            log.info("update success 1,{}", atomicReferenceFiledUpdateTest.getCount());
        }
        if (updater.compareAndSet(atomicReferenceFiledUpdateTest, 100, 120)) {
            log.info("update success 2,{}", atomicReferenceFiledUpdateTest.getCount());
        } else {
            log.info("update failed,{}", atomicReferenceFiledUpdateTest.getCount());
        }
    }
}
