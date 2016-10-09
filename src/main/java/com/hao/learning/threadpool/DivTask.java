package com.hao.learning.threadpool;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lingxue created on 10/8/16
 * @version v0.1
 **/

public class DivTask implements Runnable {

    int a,b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double re = a/b;
        System.out.println(re);
    }

    public static void main(String[] args){
        ThreadPoolExecutor exec = new ThreadPoolExecutor(0,Integer.MAX_VALUE,0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        for(int i=0;i<5;i++){
            exec.execute(new DivTask(100,i));
        }
    }
}
