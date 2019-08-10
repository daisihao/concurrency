package com.daisihao.concurrency.publish.singleton;

import com.daisihao.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 使用双重同步锁机制,但是并不安全
 * 因为会发生CUP指令重排
 */
@NotThreadSafe
public class LanhanSyncNotSafe {

    //私有的构造函数
    private LanhanSyncNotSafe() {
    }

    //单例对象
    /**
     * 下面这个方法CPU做的事情
     * 1、分配对象内存空间 memory = allocate();
     * 2、初始化对象  ctorInstance();
     * 3、设置instance指向刚才分配的内存 instance = memory
     * 其中第二步和第三部可能会发生指令重排
     * 1-2-3/1-3-2
     */
    private static LanhanSyncNotSafe instance = null;

    /**
     *  线程A执行到instance = new LanhanSyncNotSafe(),此时CPU的执行顺序是 1-3-2,并且正处于第三部
     *  此时线程B开始判断instance == null这时候就不会去创建新的时候
     *  后续直接只用这个instance就会报错
     */
    public static LanhanSyncNotSafe getInstance() {
        if (instance == null) { //双重检测机制   //B
            synchronized (LanhanSyncNotSafe.class) { //同步锁
                if (instance == null) ;
                instance = new LanhanSyncNotSafe(); // A - 3
            }
        }
        return instance;
    }
}
