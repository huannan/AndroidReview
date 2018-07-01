package com.nan.androidreview.designpattern.singleton.demo3;

/**
 * 饿汉式
 * 特点：非Lazy初始化，浪费内存；线程安全，基于ClassLoader机制避免了多线程的同步问题
 */
public class Singleton {

    //方式一：类装载的时候初始化
    private static Singleton sInstance = new Singleton();

    //方式二：类初始化的时候才去初始化
    static {
        sInstance = new Singleton();
    }

    private Singleton() {

    }

    public static synchronized Singleton getInstance() {
        return sInstance;
    }

}
