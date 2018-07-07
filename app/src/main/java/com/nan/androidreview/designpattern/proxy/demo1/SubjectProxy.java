package com.nan.androidreview.designpattern.proxy.demo1;

public class SubjectProxy implements Subject {

    private Subject mSubject;

    public SubjectProxy(Subject subject) {
        mSubject = subject;
    }

    @Override
    public void request() {
        //代理中可以增加一些自定义的逻辑，这是面向切面编程的雏形
        before();
        mSubject.request();
        after();
    }

    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }
}
