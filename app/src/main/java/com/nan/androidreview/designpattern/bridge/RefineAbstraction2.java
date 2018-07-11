package com.nan.androidreview.designpattern.bridge;

public class RefineAbstraction2 extends Abstraction {

    public RefineAbstraction2(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operator() {
        System.out.println("小杯的" + this.getImplementor().implement() + "咖啡");
    }
}
