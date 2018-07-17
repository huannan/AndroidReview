package com.nan.androidreview.designpattern.state;

public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        System.out.println("当前状态：" + context.getCurrentState());
        context.handle1();
        System.out.println("当前状态：" + context.getCurrentState());
        context.handle2();
        System.out.println("当前状态：" + context.getCurrentState());
    }
}
