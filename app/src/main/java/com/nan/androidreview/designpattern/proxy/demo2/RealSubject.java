package com.nan.androidreview.designpattern.proxy.demo2;

public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("业务逻辑");
    }
}
