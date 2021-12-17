package com.zhf.juc.lock;

import java.util.concurrent.CountDownLatch;

/**
 * @author: 曾鸿发
 * @create: 2021-12-16 20:03
 * @description：
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) {
        usingJoin();
        usingCountDownLatch();
    }

    private static void usingCountDownLatch(){
        Thread [] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(() -> {
                int result = 0;
                for(int j = 0; j < 10000; j++){
                    latch.countDown();
                }
            });
        }

        for(int i = 0; i < threads.length; i++){
            threads[i].start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end latch");
    }

    public static void usingJoin(){
        Thread[] threads = new Thread[100];

        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(() -> {
                int result = 0;
                for(int j = 0; j < 10000; j++){
                    result += j;
                }
            });
        }

        for(int i = 0; i < threads.length; i++){
            threads[i].start();
        }

        for(int i = 0; i < threads.length; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("end join");

    }
}
