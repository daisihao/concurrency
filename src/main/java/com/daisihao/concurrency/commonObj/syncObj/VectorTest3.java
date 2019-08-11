package com.daisihao.concurrency.commonObj.syncObj;

import com.daisihao.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Vector;

/**
 * 这个方法验证了Vector是一个线程不安全你的类
 */
@Slf4j
@NotThreadSafe
public class VectorTest3 {

    /**
     * failed
     * @param vector
     */
    private static void test1(Vector<Integer> vector){
        for (Integer i:vector){
            if(i.equals(3)){
                vector.remove(i);
            }
        }
    }

    /**
     * failed
     * @param vector
     */
    private static void test2(Vector<Integer> vector){

        Iterator<Integer> iterator = vector.iterator();

        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next.equals(3)){
                vector.remove(next);
            }
        }

    }

    /**
     * success
     * @param vector
     */
    private static void test3(Vector<Integer> vector){
        for(int i = 0 ;i<vector.size();i++){
            if(vector.get(i).equals(3)){
                vector.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        test1(vector);
        test2(vector);
        test3(vector);
    }

}
