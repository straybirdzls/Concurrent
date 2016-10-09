package com.hao.learning.suspend;

/**
 * Created by zhanghao on 9/25/16.
 */
public class GoodSuspend {

    public static Object object = new Object();

    public static class ChangeObjectExtend extends Thread {

        boolean suspend = false;

        public void suspendMe(){
            suspend = true;
        }

        public void resumeMe(){
            suspend = false;
            synchronized (object){
                notify();
            }
        }

        @Override
        public void run(){

        }
    }

}
