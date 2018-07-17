package com.nan.androidreview.designpattern.memento;

/**
 * 发起人角色：记录当前时刻的内部状态，负责定义哪些属于备份范围的状态，负责创建和恢复备忘录数据。
 */
public class Originator {

    private String mState = "";

    public String getState() {
        return this.mState;
    }

    public void setState(String state) {
        this.mState = state;
    }

    /**
     * 创建一个备忘录
     * @return
     */
    public Memento createMemento() {
        return new Memento(this.mState);
    }

    /**
     * 恢复一个备忘录
     * @param memento
     */
    public void restoreMemento(Memento memento) {
        this.setState(memento.getState());
    }
}
