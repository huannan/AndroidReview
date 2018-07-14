package com.nan.androidreview.designpattern.mediator;

/**
 * 卖家
 */
public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void selfMethod2() {
        System.out.println("卖家：嘿，房子整理中……房子整理好了");
    }

    public void depMethod2() {
        System.out.println("卖家：嘿，房产中介，帮我告诉买家，房子整理好了，房子可以出售");
        //通知中介者，房子可以出售
        this.mMediator.doSomething2();
    }
}
