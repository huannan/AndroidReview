package com.nan.androidreview;

public class TestThread {

    public static void main(String[] args) {

        Thread thread = new Thread(new MyRunnable());
        thread.start();

    }

    class MyThread extends Thread {

        @Override
        public void run() {
            super.run();

        }
    }

    static  class MyRunnable implements Runnable {
        @Override
        public void run() {

        }
    }


}
