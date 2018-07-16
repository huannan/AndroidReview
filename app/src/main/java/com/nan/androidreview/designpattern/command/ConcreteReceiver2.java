package com.nan.androidreview.designpattern.command;

/**
 * 例子：视觉设计师
 */
public class ConcreteReceiver2 extends Receiver{

    @Override
    public void doSomething() {
        System.out.println("设计UI");
    }
}
