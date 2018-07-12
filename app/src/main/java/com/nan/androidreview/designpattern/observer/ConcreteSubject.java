package com.nan.androidreview.designpattern.observer;

public class ConcreteSubject extends Subject {

    public void doSomething() {
        System.out.println("doSomething");
        this.notifyObservers();
    }

}
