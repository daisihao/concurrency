package com.daisihao.concurrency.publish.singleton;

/**
 * 推荐的单例模式
 * 枚举方法是最安全的单例模式
 */
public class EnumSafe {

    private EnumSafe(){}

    public static EnumSafe getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;
        private EnumSafe singleton;
        //枚举的构造方法
        //JVM来保证这个方法只被调用一次,绝对的
        Singleton(){
            singleton = new EnumSafe();
        }
        public EnumSafe getInstance(){
            return singleton;
        }
    }
}
