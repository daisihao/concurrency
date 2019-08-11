package com.daisihao.concurrency.immutable;

import com.daisihao.concurrency.annoations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 如果使用final修饰基础数据类型,那么是不可以修改的
 * 如果使用final修饰基础引用数据类型,那么不可以将它指向其他对象,但是可以修改里面的值
 * 使用final修饰引用对象,是线程不安全的
 */
@Slf4j
@NotThreadSafe
public class ImmuatbleTest {

    private static final Integer a = 1;
    private static final String b = "2";
    private static final Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
    }

    public static void main(String[] args) {
        //a=2;
        //map = new HashMap<>();
        map.put(1,3);
        log.info("{}",map.toString());

    }
}
