package com.nan.androidreview.designpattern.command;

/**
 * 例子：写代码的命令，下发给程序员
 */
public class ConcreteCommand1 extends Command {

    public ConcreteCommand1() {
        super(new ConcreteReceiver1());
    }

    public ConcreteCommand1(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.mReceiver.doSomething();
    }
}
