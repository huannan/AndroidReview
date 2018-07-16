package com.nan.androidreview.designpattern.command;

/**
 * 调用者角色：接收客户端的命令，并执行命令。
 * 例子：项目经理
 */
public class Invoker {

    private Command mCommand;

    public void setCommand(Command command) {
        this.mCommand = command;
    }

    public void action() {
        this.mCommand.execute();
    }
}
