package com.nan.androidreview.designpattern.state;

/**
 * 环境角色：定义客户端需要的接口，并且负责具体状态的切换。
 */
public class Context {

    //定义所有状态
    public static final State STATE1 = new ConcreteState1();
    public static final State STATE2 = new ConcreteState2();
    //当前状态
    private State mCurrentState;

    public Context() {
        //初始化
        STATE1.setContext(this);
        STATE2.setContext(this);
        this.mCurrentState = STATE1;
    }

    public State getCurrentState() {
        return this.mCurrentState;
    }

    public void setCurrentState(State state) {
        this.mCurrentState = state;
    }

    //行为委托给State去处理
    public void handle1() {
        this.mCurrentState.handle1();
    }

    public void handle2() {
        this.mCurrentState.handle2();
    }
}
