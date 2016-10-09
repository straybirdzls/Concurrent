package com.hao.learning.sema;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author lingxue created on 9/26/16
 * @version v0.1
 **/

public class SemapDemo implements Runnable {

    final Semaphore semaphore = new Semaphore(5);

    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId() + ":done!");
            semaphore.release();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemapDemo semapDemo = new SemapDemo();
        for(int i=0; i<20; i++){
            exec.submit(semapDemo);
        }
        exec.shutdown();
    }
}
