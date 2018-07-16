package com.nan.androidreview.designpattern.command;

/**
 * 例子：程序员
 */
public class ConcreteReceiver1 extends Receiver{

    @Override
    public void doSomething() {
        System.out.println("写代码");
    }
}
