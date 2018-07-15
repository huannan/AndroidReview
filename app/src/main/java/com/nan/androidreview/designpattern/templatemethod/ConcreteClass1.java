package com.nan.androidreview.designpattern.templatemethod;

/**
 * 具体模板：实现父类所定义的一个或多个抽象方法，也就是父类定义的基本方法在子类中得以实现。
 */
public class ConcreteClass1 extends AbstractClass {
    @Override
    protected void method1() {
        System.out.println("ConcreteClass1 : method1");
    }

    @Override
    protected void method2() {
        System.out.println("ConcreteClass1 : method2");
    }

    @Override
    protected boolean hookMethod() {
        return true;
    }
}
