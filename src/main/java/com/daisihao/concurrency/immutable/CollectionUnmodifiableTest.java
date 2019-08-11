package com.daisihao.concurrency.immutable;

import com.daisihao.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class CollectionUnmodifiableTest {

    private static final Integer a = 1;
    private static final String b = "2";
    private static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        //使用Collections.unmodifiableMap修饰的值,后面不可以再修改里面的值
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //a=2;
        //map = new HashMap<>();
        map.put(1,3);
        log.info("{}",map.toString());

    }
}
