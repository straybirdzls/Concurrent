package com.hao.learning.suspend;

/**
 * Created by zhanghao on 9/25/16.
 */
public class BadSuspend {

    public static Object object = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("T1");
    static ChangeObjectThread t2 = new ChangeObjectThread("T2");

    public static class ChangeObjectThread extends Thread {

        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run(){
            synchronized (object){
                System.out.println("In thread: " + getName());
                Thread.currentThread().suspend();
            }
        }

        public static void main(String[] args) throws InterruptedException{
            t1.start();
            Thread.sleep(2000);
            t1.resume();
            t2.start();
            t2.resume();
        }
    }

}
