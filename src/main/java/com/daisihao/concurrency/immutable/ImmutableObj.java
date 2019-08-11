package com.daisihao.concurrency.immutable;

import com.daisihao.concurrency.annoations.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

@ThreadSafe
@Slf4j
public class ImmutableObj {

    //ImmutableList
    private final static ImmutableList<Integer> list = ImmutableList.of(1,2,3);
    //ImmutableSet
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
    //ImmutableMap
    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4);
    private final static ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder().put(1,2).build();

    public static void main(String[] args) {
        log.info("{}",map2);
    }

}
