package com.nan.androidreview.designpattern.mediator;

public class ConcreteMediator extends Mediator {

    @Override
    public void doSomething1() {
        this.mColleague2.selfMethod2();
    }

}
