package com.nan.androidreview.designpattern.chain;

/**
 * 抽象处理者
 * 抽象的处理者实现三个职责：
 * 1. 定义一个对外开放的请求的处理方法handleMessage模板方法
 * 2. 定义一个链的编排方法setNext，设置下一个处理者
 * 3. 定义了具体的请求者必须实现的两个方法：定义自己能够处理的级别getHandlerLevel和具体的处理任务doSomething方法
 */
public abstract class Handler {

    private Handler mNextHandler;

    public final Response handleRequest(Request request) {
        Response response;
        //判断是否是自己的处理级别
        if (request.getRequestLevel() == this.getHandlerLevel()) {
            response = this.doSomething(request);
        } else if (this.mNextHandler != null) {
            //不属于自己的处理级别，交给下一个处理
            response = this.mNextHandler.handleRequest(request);
        } else {
            //没有适当的处理者,业务自行处理
            response = new Response("没有适当的处理者");
        }
        return response;
    }

    public void setNextHandler(Handler nextHandler) {
        this.mNextHandler = nextHandler;
    }

    protected abstract int getHandlerLevel();

    protected abstract Response doSomething(Request request);
}
