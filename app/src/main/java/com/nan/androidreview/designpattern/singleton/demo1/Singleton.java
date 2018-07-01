package com.nan.androidreview.designpattern.singleton.demo1;

/**
 * 懒汉式
 * 特点：Lazy初始化；线程安全，但是由于每次需要同步性能较低，不建议使用
 */
public class Singleton {

    private static Singleton sInstance;

    private Singleton() {

    }

    public static synchronized Singleton getInstance() {
        if (sInstance == null) {
            sInstance = new Singleton();
        }
        return sInstance;
    }

}
