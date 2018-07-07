package com.nan.androidreview.designpattern.adapter.demo1;

public class Adapter extends Adaptee implements Target {
    @Override
    public void volt5() {
        System.out.println("5V电压");
    }

    public void volt220() {
        super.volt220();
    }
}
