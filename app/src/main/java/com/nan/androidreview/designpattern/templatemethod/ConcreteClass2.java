package com.nan.androidreview.designpattern.templatemethod;

public class ConcreteClass2 extends AbstractClass {
    @Override
    protected void method1() {
        System.out.println("ConcreteClass2 : method1");
    }

    @Override
    protected void method2() {
        System.out.println("ConcreteClass2 : method2");
    }

    @Override
    protected boolean hookMethod() {
        return false;
    }
}
