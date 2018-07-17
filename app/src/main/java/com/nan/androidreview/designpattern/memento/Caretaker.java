package com.nan.androidreview.designpattern.memento;

/**
 * 备忘录管理员角色：对备忘录进行管理、保存和提供备忘录。实际开发中这个管理员角色一般会比较复杂。
 */
public class Caretaker {

    private Memento mMemento;

    public void setMemento(Memento memento) {
        this.mMemento = memento;
    }

    public Memento getMemento() {
        return this.mMemento;
    }
}
