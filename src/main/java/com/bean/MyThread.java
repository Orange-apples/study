package com.bean;

import java.util.concurrent.TimeUnit;

public class MyThread implements Runnable {
    int i;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + ":===start===");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + ":===end===");
    }

    synchronized void print() throws InterruptedException {
        String name = Thread.currentThread().getName();
        System.out.println(name + "：获取到了锁");
        for (int j = 0; j < 10; j++) {
            Thread.sleep(500);
            System.out.println(name + ":" + (++i));
            if (j == 5) {

            }
        }
    }
    public void test(){
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread, "1");
        t1.setDaemon(true);
        Thread t2 = new Thread(myThread, "2");
        Thread t3 = new Thread(myThread, "3");
        t1.start();
        t2.start();
    }

    public static void main(String[] args) throws Exception {

    }
}
