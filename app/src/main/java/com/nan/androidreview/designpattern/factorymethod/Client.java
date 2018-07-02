package com.nan.androidreview.designpattern.factorymethod;

public class Client {

    public static void main(String[] args) {
        ConcreteFactory factory = new ConcreteFactory();
        factory.createProduct(ConcreteProduct1.class);
    }

}
