package com.nan.androidreview.designpattern.decorator;

public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteDecorator1(new ConcreteDecorator2(new ConcreteComponent()));
        component.operate();
    }

}
