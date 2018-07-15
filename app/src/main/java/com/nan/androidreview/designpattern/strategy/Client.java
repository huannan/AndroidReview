package com.nan.androidreview.designpattern.strategy;

public class Client {

    public static void main(String[] args) {
        Strategy s1 = new ConcreteStrategy1();
        Strategy s2 = new ConcreteStrategy2();
        Context context = new Context(s1);

        context.doSomething();

        context.setStrategy(s2);
        context.doSomething();
    }
}
