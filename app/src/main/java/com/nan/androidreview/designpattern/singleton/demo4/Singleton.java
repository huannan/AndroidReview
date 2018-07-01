package com.nan.androidreview.designpattern.singleton.demo4;

/**
 * 静态内部类
 * 特点：饿汉式只要类装载或者类初始化的时候单例初始化，但是静态内部类的方式确保调用getInstance才Lazy初始化；线程安全；推荐使用
 */
public class Singleton {

    private static class SingletonHolder {
        private static final Singleton sInstance = new Singleton();
    }

    private Singleton() {

    }

    public static Singleton getInstance() {
        return SingletonHolder.sInstance;
    }

}
