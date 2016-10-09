package com.hao.learning.threadpool.forkjoin;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author lingxue created on 10/9/16
 * @version v0.1
 **/

public class CountTask  extends RecursiveTask<Long>{

    private static int THRESHOLD = 10000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canComupte = (end-start) < THRESHOLD;
        if(canComupte){
            for(long i=start;i<=end;i++){
                sum+=i;
            }
        }
        else{
            long step = (start+end)/100;
            ArrayList<CountTask> subTasks = new ArrayList<>();
            long pos = start;
            for(long i=0;i<100;i++){
                long lastOne = pos + step;
                if(lastOne > end){
                    lastOne = end;
                }
                CountTask subTask = new CountTask(pos,lastOne);
                pos += step+1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for(CountTask t:subTasks){
                sum += t.join();
            }
        }
        return sum;
    }

    public static void main(String[] args){
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0,200000L);
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try{
            System.out                                                                            .println(result.get());
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        catch (ExecutionException e){
            e.printStackTrace();
        }
    }
}
