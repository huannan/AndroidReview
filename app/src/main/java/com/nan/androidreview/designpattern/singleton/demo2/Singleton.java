package com.nan.androidreview.designpattern.singleton.demo2;

/**
 * 双检锁/双重校验锁（DCL，即 double-checked locking）
 * 特点：懒汉式的改进版，Lazy初始化；线程安全，且在多线程情况下能保持高性能
 */
public class Singleton {

    private static Singleton sInstance;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (sInstance == null) {
            synchronized (Singleton.class) {
                if (sInstance == null) {
                    sInstance = new Singleton();
                }
            }
        }
        return sInstance;
    }

}
