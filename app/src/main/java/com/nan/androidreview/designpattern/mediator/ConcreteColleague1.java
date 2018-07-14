package com.nan.androidreview.designpattern.mediator;

/**
 * 买家
 */
public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    /**
     * 自发行为：同事本身的行为，例如改变对象自身的状态，处理自己的行为
     */
    public void selfMethod1() {
        System.out.println("买家：付款，购买房子");
    }

    /**
     * 依赖行为：自己不能完成的业务，必须依赖中介者才能完成的行为
     */
    public void depMethod1() {
        System.out.println("买家：嘿，房产中介，我要买房");
        //通知中介者，我要买房
        this.mMediator.doSomething1();
    }
}
