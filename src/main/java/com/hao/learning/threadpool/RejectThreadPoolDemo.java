package com.hao.learning.threadpool;

import java.util.concurrent.*;

/**
 * @author lingxue created on 9/27/16
 * @version v0.1
 **/

public class RejectThreadPoolDemo {

    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ":, Thread Id: " +
            Thread.currentThread().getId());
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        MyTask myTask = new MyTask();
        ExecutorService exec = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10), Executors.defaultThreadFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r + " is discard'");
                    }
                });

        for(int i=0; i<Integer.MAX_VALUE;i++){
            exec.submit(myTask);
        }

    }
}
