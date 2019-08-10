package com.daisihao.concurrency.publish.singleton;

import com.daisihao.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 应为双重检测机制的主要问题是如果发生了指令重排可能会返回空的实例
 * 那么我们需要做的事情就是限制指令重排,这里使用了volatile限制指令重排
 */
@ThreadSafe
public class LanhanSyncVolatileSafe {

    //私有的构造函数
    private LanhanSyncVolatileSafe() {
    }

    //单例对象
    private static volatile LanhanSyncVolatileSafe instance = null;

    public static LanhanSyncVolatileSafe getInstance() {
        if (instance == null) {
            synchronized (LanhanSyncVolatileSafe.class) {
                if (instance == null) ;
                instance = new LanhanSyncVolatileSafe();
            }
        }
        return instance;
    }
}
