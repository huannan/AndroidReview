package com.nan.androidreview.designpattern.proxy.demo1;

public class Client {

    public static void main(String[] args) {
        Subject subject = new SubjectProxy(new RealSubject());
        subject.request();
    }

}
