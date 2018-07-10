package com.nan.androidreview.designpattern.flyweight;

public class Client {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            FlyweightFactory.getFlyweight("1");
        }

    }

}
