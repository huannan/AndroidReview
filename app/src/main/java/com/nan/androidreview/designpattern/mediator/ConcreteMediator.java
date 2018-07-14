package com.nan.androidreview.designpattern.mediator;

/**
 * 例子：房产中介
 */
public class ConcreteMediator extends Mediator {

    @Override
    public void doSomething1() {
        System.out.println("房产中介：帮你找到卖家");
        //通知卖家整理好房子
        this.mColleague2.selfMethod2();
        this.mColleague2.depMethod2();
    }

    @Override
    public void doSomething2() {
        System.out.println("房产中介：帮你找到买家");
        this.mColleague1.selfMethod1();
    }

}
