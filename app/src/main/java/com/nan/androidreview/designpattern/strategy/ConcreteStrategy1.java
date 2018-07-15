package com.nan.androidreview.designpattern.strategy;

/**
 * 具体策略角色
 * 实现抽象策略中的操作，该类含有具体的算法。
 */
public class ConcreteStrategy1 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("执行策略1");
    }
}
