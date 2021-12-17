package com.zhf.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: 曾鸿发
 * @create: 2021-12-16 16:37
 * @description：可重入锁案例3
 **/
public class ReentrantLockDemo3 {
    Lock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();
            for(int i = 0; i < 3; i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用tryLock进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据tryLock的返回值来判定是否锁定
     * 也可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unlock的处理，必须放到finally中
     */
    void m2(){
        boolean locked = false;

        try {
            locked =lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo3 demo3 = new ReentrantLockDemo3();
        new Thread(demo3::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(demo3::m2).start();
    }
}
