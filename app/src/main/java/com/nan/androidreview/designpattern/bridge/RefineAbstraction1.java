package com.nan.androidreview.designpattern.bridge;

/**
 * RefineAbstraction是修正抽象化角色，通过引用实现化角色Implementor对抽象化角色Abstraction进行修正
 * RefineAbstraction1是咖啡的实现类，代表大杯的咖啡
 */
public class RefineAbstraction1 extends Abstraction {

    public RefineAbstraction1(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operator() {
        System.out.println("大杯的" + this.getImplementor().implement() + "咖啡");
    }
}
