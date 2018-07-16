package com.nan.androidreview.designpattern.command;

/**
 * 命令角色：需要执行的所有命令都在这里声明。
 */
public abstract class Command {

    protected Receiver mReceiver;

    public Command(Receiver receiver) {
        this.mReceiver = receiver;
    }

    public abstract void execute();
}
