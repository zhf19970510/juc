package com.zhf.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: 曾鸿发
 * @create: 2021-12-16 17:28
 * @description：
 **/
public class ReentrantLockDemo5 extends Thread{

    private static ReentrantLock lock = new ReentrantLock(true);    // 参数为true表示为公平锁,请对比输出结果

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo5 demo5 = new ReentrantLockDemo5();
        Thread th1 = new Thread(demo5);
        Thread th2 = new Thread(demo5);
        th1.start();
        th2.start();
    }
}
