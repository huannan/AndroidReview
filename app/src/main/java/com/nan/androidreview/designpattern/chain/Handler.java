package com.nan.androidreview.designpattern.chain;

public abstract class Handler {

    private Handler mNextHandler;

    public final Response handleRequest(Request request) {
        Response response;
        if (request.getRequestLevel() == this.getHandlerLevel()) {
            response = this.doSomething(request);
        } else if (this.mNextHandler != null) {
            response = this.mNextHandler.handleRequest(request);
        } else {
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
