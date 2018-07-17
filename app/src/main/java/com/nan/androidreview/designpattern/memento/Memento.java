package com.nan.androidreview.designpattern.memento;

/**
 * 备忘录角色：负责存储发起人对象的内部状态，在需要的时候提供发起人需要的内部状态。
 */
public class Memento {

    private String mState;

    public Memento(String state) {
        this.mState = state;
    }

    public String getState() {
        return mState;
    }
}
