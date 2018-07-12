package com.nan.androidreview.designpattern.mediator;

public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void selfMethod1() {

    }

    public void depMethod1() {
        this.mMediator.doSomething1();
    }
}
