package com.nan.androidreview.designpattern.observer;

public class ConcreteObserver implements Observer {

    @Override
    public void update() {
        System.out.println("收到消息");
    }
}
