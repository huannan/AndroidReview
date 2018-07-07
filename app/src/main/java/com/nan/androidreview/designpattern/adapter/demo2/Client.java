package com.nan.androidreview.designpattern.adapter.demo2;


public class Client {
    public static void main(String[] args) {
        Target t1 = new ConcreteTarget();
        t1.volt5();

        Target t2 = new Adapter(new Adaptee());
        t2.volt5();
    }
}
