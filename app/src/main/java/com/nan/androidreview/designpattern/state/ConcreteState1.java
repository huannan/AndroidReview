package com.nan.androidreview.designpattern.state;

/**
 * 具体状态角色：每一个具体状态必须完成两个职责：
 * 1. 本状态的行为管理以及趋向状态处理，通俗地说，就是本状态下要做的事情。
 * 2. 以及本状态如何过渡到其他状态。
 */
public class ConcreteState1 extends State{

    @Override
    public void handle1() {
        System.out.println("ConcreteState1下必须处理的逻辑");
    }

    @Override
    public void handle2() {
        //本状态不能处理，需要切换状态，并且委托给下一个状态去处理
        System.out.println("当前状态" + this + "不能处理，切换到"+Context.STATE2);
        this.mContext.setCurrentState(Context.STATE2);
        this.mContext.handle2();
    }
}
