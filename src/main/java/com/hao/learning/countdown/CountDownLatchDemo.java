package com.hao.learning.countdown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lingxue created on 9/26/16
 * @version v0.1
 **/

public class CountDownLatchDemo implements Runnable {

    static final CountDownLatch countDownLatch = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try{
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete");
            countDownLatch.countDown();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for(int i=0; i<10; i++){
            exec.submit(demo);
        }
        countDownLatch.await();

        System.out.println("Fire!");
        exec.shutdown();
    }
}
