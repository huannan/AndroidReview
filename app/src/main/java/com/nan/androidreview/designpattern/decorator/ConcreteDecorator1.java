package com.nan.androidreview.designpattern.decorator;

public class ConcreteDecorator1 extends Decorator{

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        decorate1();
        super.operate();
    }

    public void decorate1() {
        System.out.println("修饰方法1");
    }
}
