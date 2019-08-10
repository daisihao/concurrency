package com.daisihao.concurrency.publish;

import com.daisihao.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 如果有两个线程同时调用,都修改了数组的第一个值
 * 那么程序获得到的返回值可能会发生错误
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString( unsafePublish.getStates()));
        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString( unsafePublish.getStates()));
    }
}
