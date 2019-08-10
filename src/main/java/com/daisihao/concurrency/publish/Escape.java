package com.daisihao.concurrency.publish;

import com.daisihao.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NotThreadSafe
public class Escape {

    private int thisCanBeEscape = 0;
    public Escape (){
        new InnnerClass();
    }
    private class InnnerClass{
        public InnnerClass(){
            log.info("我是内部类：{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
