package com.nan.androidreview.designpattern.proxy.demo1;

public class SubjectProxy implements Subject {

    private Subject mSubject;

    public SubjectProxy(Subject subject) {
        mSubject = subject;
    }

    @Override
    public void request() {
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
