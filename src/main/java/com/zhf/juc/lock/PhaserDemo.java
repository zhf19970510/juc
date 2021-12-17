package com.zhf.juc.lock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author: 曾鸿发
 * @create: 2021-12-16 22:00
 * @description：
 **/
public class PhaserDemo {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli){
        try {
            TimeUnit.MICROSECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(5);

        for(int i = 0; i < 5; i++){
            final int nameIndex = i;
            new Thread(() -> {
                Person person = new Person("person " + nameIndex);
                person.arrive();
                phaser.arriveAndAwaitAdvance();

                person.eat();
               phaser.arriveAndAwaitAdvance();

                person.leave();
                phaser.arriveAndAwaitAdvance();

            }).start();
        }
    }

    static class MarriagePhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("所有人到齐了！");
                    return false;
                case 1:
                    System.out.println("所有人吃完了！");
                    return false;
                case 2:
                    System.out.println("所有人离开了！");
                    System.out.println("婚礼结束！");
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person{
        String name;

        public Person(String name){
            this.name = name;
        }

        public void arrive(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
        }

        public void eat(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完！\n", name);
        }

        public void leave(){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！\n", name);
        }
    }
}
