package com.zhf.juc.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: 曾鸿发
 * @create: 2021-12-15 16:09
 * @description：
 **/
public class AtomicIntegerDemo {

    AtomicInteger count = new AtomicInteger(0);

    /*synchronized*/ void m(){
        for(int i = 0; i < 10000; i++){
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        AtomicIntegerDemo t = new AtomicIntegerDemo();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        System.out.println(t.count);
    }
}
