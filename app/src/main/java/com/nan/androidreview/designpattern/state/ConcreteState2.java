package com.nan.androidreview.designpattern.state;

public class ConcreteState2 extends State{

    @Override
    public void handle1() {
        System.out.println("当前状态" + this + "不能处理，切换到"+Context.STATE1);
        this.mContext.setCurrentState(Context.STATE1);
        this.mContext.handle1();
    }

    @Override
    public void handle2() {

        System.out.println("ConcreteState2下必须处理的逻辑");
    }
}
