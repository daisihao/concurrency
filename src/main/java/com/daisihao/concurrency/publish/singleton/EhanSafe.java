package com.daisihao.concurrency.publish.singleton;

import com.daisihao.concurrency.annoations.ThreadSafe;

/**
 * 单例模式
 * 恶汉模式
 * 单例实例在类装载的时候被创建
 * 静态代码块是按照代码编写顺序执行的
 *
 */
@ThreadSafe
public class EhanSafe {

    private EhanSafe() {
    }

    private static EhanSafe instance = new EhanSafe();

    public static EhanSafe getInstance() {
        return instance;
    }

}
