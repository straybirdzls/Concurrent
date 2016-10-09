package com.hao.learning.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lingxue created on 9/26/16
 * @version v0.1
 **/

public class ConditionLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try{
            lock.lock();
            condition.await();
            System.out.println("Condition lock run");
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ConditionLock conditionLock = new ConditionLock();
        Thread thread1 = new Thread(conditionLock);
        thread1.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        lock.unlock();
    }

}
