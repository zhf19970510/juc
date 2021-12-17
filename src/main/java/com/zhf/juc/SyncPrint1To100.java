package com.zhf.juc;

/**
 * @author: 曾鸿发
 * @create: 2021-12-13 08:50
 * @description：使用三个线程顺序打印 1 - 100
 **/
public class SyncPrint1To100 {

    private Object prev;

    private Object current;

    private static int count = 0;

    public SyncPrint1To100(){

    }

    public SyncPrint1To100(Object prev, Object current){
        this.prev = prev;
        this.current = current;
    }

    public void print1To100(){
        while (count < 100){
            synchronized (prev){            // 先获取上一个锁对象
                synchronized (current){     // 获取当前锁对象
                    count++;
                    System.out.println(count);
                    current.notifyAll();
                }

                try {
                    if(count == 100){
                        prev.notifyAll();               // 当 count = 100 时，释放所有的锁，防止程序最后还有锁处于等待状态
                    }else{
                        prev.wait();                    // 释放当前锁，让其他线程进行抢占该锁
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

    }


    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        final SyncPrint1To100 syncPrint1To100A = new SyncPrint1To100(c, a);

        final SyncPrint1To100 syncPrint1To100B = new SyncPrint1To100(a, b);

        final SyncPrint1To100 syncPrint1To100C = new SyncPrint1To100(b, c);

        new Thread(syncPrint1To100A::print1To100).start();
        Thread.sleep(100);      // 保证线程一开始创建时候的正确执行顺序
        new Thread(syncPrint1To100B::print1To100).start();
        Thread.sleep(100);
        new Thread(syncPrint1To100C::print1To100).start();
    }
}
