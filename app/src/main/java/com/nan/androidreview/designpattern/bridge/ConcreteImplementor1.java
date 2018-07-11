package com.nan.androidreview.designpattern.bridge;

/**
 * ConcreteImplementor具体的实现化角色
 * ConcreteImplementor咖啡的调料的实现类，代表加糖
 *
 */
public class ConcreteImplementor1 implements Implementor {
    @Override
    public String implement() {
        return "加糖";
    }
}
