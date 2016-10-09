package com.hao.learning.interrupt;

/**
 * Created by zhanghao on 9/24/16.
 *
 * Sleep 中的中断，要重置中断标志位
 *
 */
public class InterruptThread {

    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread(){

            @Override
            public void run(){
                while (true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.print("interrupt");
                        break;
                    }
                    try{
                        Thread.sleep(2000);
                    }
                    catch (InterruptedException e){
                        System.out.println("Interrupted when sleep");
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }
}
