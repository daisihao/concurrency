package com.daisihao.concurrency.publish.singleton;

import com.daisihao.concurrency.annoations.NotThreadSafe;

/**
 * 单例模式
 * 懒汉模式
 * 单例对象在类的第一次调用时初始化
 * 在单线程是ok的,但是在多线程模式下是有问题的
 */
@NotThreadSafe
public class LanhanNotSafe {

    //私有的构造函数
    private LanhanNotSafe(){
    }
    //单例对象
    private static LanhanNotSafe instance = null;

    //静态的工厂方法
    public static LanhanNotSafe getInstance(){
        /**
         * 在多线程的情况下,如果两个线程同时调用这个方法
         * 那么他们获取的instance都是null
         * 所以可能会创建两个实例
         */
        if(instance==null){
            instance = new LanhanNotSafe();
        }
        return instance;
    }

}
