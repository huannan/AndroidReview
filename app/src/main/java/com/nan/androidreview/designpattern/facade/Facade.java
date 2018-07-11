package com.nan.androidreview.designpattern.facade;

public class Facade {

    ClassA mA = new ClassA();
    ClassB mB = new ClassB();
    ClassC mC = new ClassC();

    public void methodA() {
        mA.doSomethingA();
    }

    public void methodB() {
        mB.doSomethingB();
    }

    public void methodC() {
        mC.doSomethingC();
    }
}
