package com.nan.androidreview.designpattern.proxy.demo1;

public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("业务逻辑");
    }
}
