package com.zhf.juc.lock;

import java.util.concurrent.Exchanger;

/**
 * @author: 曾鸿发
 * @create: 2021-12-17 22:12
 * @description：
 **/
public class ExchangerDemo {

    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {


        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + s);
        }, "T1").start();

        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - " + s);
        }, "T2").start();

        
    }
}
