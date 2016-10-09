package com.hao.learning.atomicity;

/**
 * Created by zhanghao on 9/24/16.
 */
public class MultiThreadLong {

    public static long t = 0;

    public static class ChangeT implements Runnable{
        private long changeTo;

        public ChangeT(long changeTo) {
            this.changeTo = changeTo;
        }

        public void run() {
            while (true){
                MultiThreadLong.t = changeTo;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable{

        public void run() {
            while (true){
                long tmp = MultiThreadLong.t;
                if(tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444){
                    System.out.print(tmp);
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args){
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(333L)).start();
        new Thread(new ChangeT(-444L)).start();
        new Thread(new ReadT()).start();
    }

}
