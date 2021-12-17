package com.zhf.juc.c_01;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 关键字，使一个变量在多个线程间可见
 * A B线程都用到一个变量，java默认是A线程中保留一份copy，这样如果B线程修改了该变量，则A线程未必知道
 * 使用volatile关键字，会让所有线程都会读到变量的修改值
 *
 *
 * @author: 曾鸿发
 * @create: 2021-12-15 13:14
 *
 * volatile并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 **/
public class VolatileNotSync {
    volatile int count = 0;
    synchronized void m(){
        for(int i = 0; i < 10000; i++){
            count++;
        }
    }

    public static void main(String[] args) {
        VolatileNotSync t = new VolatileNotSync();
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }

}
