/**
 * 信号量：主要用于限流
 *
 * AbstractQueuedSynchronizer
 *
 * 这些类内部都实现了 AQS : AbstractQueuedSynchronizer
 */
package com.zhf.juc.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: 曾鸿发
 * @create: 2021-12-17 11:16
 * @description：
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore s = new Semaphore(2, true);   // 第二个参数可以表示为是公平锁还是非公平锁

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("t1 is running");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("t1 is running");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t1").start();

        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("t2 is running");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("t2 is running");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
