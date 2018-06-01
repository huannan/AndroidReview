package com.nan.androidreview;

public class TestThread {

    static int i1; int geti1() { return i1; }

    volatile int i2; int geti2() { return i2; }

    int i3; synchronized int geti3() { return i3; }

    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable("run 1");

        Thread thread = new Thread(myRunnable);
        thread.start();

    }

    public synchronized void test1() {

    }

    public void test2() {
        synchronized (this) {

        }
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            super.run();

        }
    }

    static class MyRunnable implements Runnable {

        volatile private String mName;

        public MyRunnable(String name) {
            mName = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(mName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
