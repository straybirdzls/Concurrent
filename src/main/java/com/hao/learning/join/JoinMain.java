package com.hao.learning.join;

/**
 * Created by zhanghao on 9/25/16.
 */
public class JoinMain {

    public volatile static int i = 0;

    public static class AddThred extends Thread {

        @Override
        public void run(){
            for(i=0;i<10000;i++);
        }

    }

    public static void main(String[] args) throws InterruptedException{
        AddThred addThred = new AddThred();
        addThred.start();
        addThred.join();
        System.out.println(i);
        System.out.println(i);

    }

}

