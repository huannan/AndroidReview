package com.nan.androidreview.designpattern.command;

public class Client {

    public static void main(String[] args) {
        Invoker invoker = new Invoker();

        invoker.setCommand(new ConcreteCommand1());
        invoker.action();

        invoker.setCommand(new ConcreteCommand2());
        invoker.action();
    }
}
