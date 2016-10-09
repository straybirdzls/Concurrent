package com.hao.learning.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lingxue created on 9/26/16
 * @version v0.1
 **/

public class ReadWriteLockDemo {

    private static int data = 0;
    private static Lock reentranLock = new ReentrantLock();
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    public int readData(Lock lock){
        try{
            lock.lock();
            Thread.sleep(1000);
            return data;
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        return data;
    }

    public void writeData(Lock lock, int data){
        try{
            lock.lock();
            Thread.sleep(1000);
            ReadWriteLockDemo.data = data;
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        long start = System.currentTimeMillis();
        ExecutorService exec = Executors.newFixedThreadPool(20);
        ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                demo.readData(readLock);
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                demo.writeData(writeLock, new Random().nextInt());
            }
        };

        List<Thread> threads = new ArrayList<>();

        for(int i=0;i<18;i++){
            Thread t1 = new Thread(readRunnable);
            t1.start();
            threads.add(t1);
        }

        for(int i=0;i<2;i++){
            Thread t2 = new Thread(writeRunnable);
            t2.start();
            threads.add(t2);
        }

        for(Thread thread : threads){
            thread.join();
        }

        System.out.println("Time Cost: " + (System.currentTimeMillis() - start));
    }
}
