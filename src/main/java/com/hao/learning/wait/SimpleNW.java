package com.hao.learning.wait;

/**
 * Created by zhanghao on 9/24/16.
 *
 * 使用wait和notify都需要对象锁
 *
 */
public class SimpleNW {

    final static Object object = new Object();

    public static class T1 implements Runnable {

        @Override
        public void run() {
            synchronized (object) {
                try {
                    System.out.println(System.currentTimeMillis() + ": T1 start");
                    object.wait();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis() + ": T1 end");
            }
        }
    }

    public static class T2 implements Runnable {

        @Override
        public void run() {
            synchronized (object){
                System.out.println(System.currentTimeMillis() + ": T2 start");
                object.notify();
                System.out.println(System.currentTimeMillis() + ": T2 end");
                try{
                    Thread.sleep(2000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        new Thread(new T1()).start();
        new Thread(new T2()).start();
    }
}
