package com.nan.androidreview.designpattern.command;

/**
 * 接收者角色：该角色就是干活的角色，命令传递到这里是应该被执行的。
 * 例子：程序员、视觉设计师的基类
 */
public abstract class Receiver {

    public abstract void doSomething();

}
