package com.nan.androidreview.designpattern.strategy;

/**
 * Context封装角色：它也叫做上下文角色
 * 起承上启下封装作用，屏蔽高层模块对策略、算法的直接访问，封装可能存在的变化。
 */
public class Context {

    private Strategy mStrategy;

    public Context(Strategy strategy) {
        this.mStrategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        mStrategy = strategy;
    }

    public void doSomething() {
        this.mStrategy.doSomething();
    }
}
