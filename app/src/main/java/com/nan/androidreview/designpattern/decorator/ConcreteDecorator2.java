package com.nan.androidreview.designpattern.decorator;

public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        decorate2();
        super.operate();
    }

    public void decorate2() {
        System.out.println("修饰方法2");
    }
}
