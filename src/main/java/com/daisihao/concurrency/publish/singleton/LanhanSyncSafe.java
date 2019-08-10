package com.daisihao.concurrency.publish.singleton;


import com.daisihao.concurrency.annoations.NotRecommend;
import com.daisihao.concurrency.annoations.ThreadSafe;

@ThreadSafe
@NotRecommend
public class LanhanSyncSafe {

    //私有的构造函数
    private LanhanSyncSafe(){
    }
    //单例对象
    private static LanhanSyncSafe instance = null;

    //静态的工厂方法
    //在静态方法上加上synchronized可以保证多线程场景下只有一个线程能够执行这个方法
    //但是这种方式并不推荐,因为在同一时刻只有一个线程可以访问,会带来性能的开销
    public static synchronized LanhanSyncSafe getInstance(){
        if(instance==null){
            instance = new LanhanSyncSafe();
        }
        return instance;
    }
}
