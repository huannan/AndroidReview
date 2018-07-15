package com.nan.androidreview.designpattern.templatemethod;

/**
 * 抽象模板
 */
public abstract class AbstractClass {

    /**
     * 基本方法：基本方法也叫做基本操作，是由子类实现的方法，并且在模板方法被调用。
     */
    protected abstract void method1();

    protected abstract void method2();

    /**
     * 钩子函数：子类可以通过钩子函数（Hook Method）约束父类的行为。
     *
     * @return
     */
    protected abstract boolean hookMethod();

    /**
     * 模板方法：可以有一个或几个，一般是一个具体方法，也就是一个框架，实现对基本方法的调度，完成固定的逻辑。
     * 为了防止恶意的操作，一般模板方法都加上final关键字，不允许被覆写。
     */
    public final void templateMethod() {
        method1();
        if (hookMethod()) {
            method2();
        }
    }
}
