package com.nan.androidreview.designpattern.state;

/**
 * 抽象状态角色：接口或抽象类，负责对象状态定义，并且封装环境角色以实现状态切换。
 */
public abstract class State {

    //抽象环境中声明一个环境角色，提供各个状态类自行访问。
    protected Context mContext;

    public void setContext(Context context) {
        this.mContext = context;
    }

    //提供所有状态的抽象行为，由各个实现类实现。
    public abstract void handle1();
    public abstract void handle2();

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
