package com.nan.androidreview.designpattern.chain;

public class Client {

    public static void main(String[] args) {
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();

        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        Response response1 = handler1.handleRequest(new Request(1, "请求A"));
        System.out.println(response1.getReslust());

        Response response2 = handler1.handleRequest(new Request(2, "请求B"));
        System.out.println(response2.getReslust());

        Response response3 = handler1.handleRequest(new Request(3, "请求C"));
        System.out.println(response3.getReslust());
    }
}
