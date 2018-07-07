package com.nan.androidreview.designpattern.proxy.demo2;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {

        RealSubject subject = new RealSubject();

        //运行的时候动态产生一个代理对象，在实现阶段不用关心代理谁
        Subject proxy = (Subject) Proxy.newProxyInstance(
                subject.getClass().getClassLoader(),
                new Class[]{Subject.class},
                new SubjectIH(subject));

        proxy.request();

    }

}
