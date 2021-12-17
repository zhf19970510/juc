package com.zhf.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: 曾鸿发
 * @create: 2021-12-16 15:51
 * @description：可重入锁案例1
 **/
public class ReentrantLockDemo1 {

    synchronized void m1(){
        for(int i = 0; i < 10; i++){
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(i);
            if(i == 2){
                m2();
            }
        }
    }

    synchronized void m2(){
        System.out.println("m2 ...");
    }

    public static void main(String[] args) {
        ReentrantLockDemo1 demo1 = new ReentrantLockDemo1();

        new Thread(demo1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
