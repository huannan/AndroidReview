package com.nan.androidreview.designpattern.mediator;

public class Client {

    public static void main(String[] args) {
        Mediator mediator = new ConcreteMediator();
        ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator);
        ConcreteColleague1 colleague2 = new ConcreteColleague1(mediator);


    }

}
