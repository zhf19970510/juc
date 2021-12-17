package com.zhf.juc.lock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author: 曾鸿发
 * @create: 2021-12-17 08:55
 * @description：
 **/
public class PhaserDemo2 {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {

            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了！" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了！" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了！" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘抱抱！" + registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {

            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场！\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完!\n", name);
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开！\n", name);


            phaser.arriveAndAwaitAdvance();
        }

        private void hug(){
            if(name.equals("新郎") || name.equals("新娘")){
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 洞房！\n", name);
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();

            eat();

            leave();

            hug();
        }
    }
}
