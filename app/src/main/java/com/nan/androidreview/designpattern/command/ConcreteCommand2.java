package com.nan.androidreview.designpattern.command;

/**
 * 例子：设计UI的命令，下发给视觉设计师
 */
public class ConcreteCommand2 extends Command {

    public ConcreteCommand2() {
        super(new ConcreteReceiver2());
    }

    public ConcreteCommand2(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void execute() {
        this.mReceiver.doSomething();
    }
}
