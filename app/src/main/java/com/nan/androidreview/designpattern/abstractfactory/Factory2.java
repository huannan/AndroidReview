package com.nan.androidreview.designpattern.abstractfactory;

public class Factory2 extends AbstractFactory {

    @Override
    public ProductA createProductA() {
        return new ConcreteProductA2() {
        };
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB2();
    }
}
