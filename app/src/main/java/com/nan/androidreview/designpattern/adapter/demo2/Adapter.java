package com.nan.androidreview.designpattern.adapter.demo2;

public class Adapter implements Target {

    private Adaptee mAdaptee;

    public Adapter(Adaptee adaptee) {
        mAdaptee = adaptee;
    }

    @Override
    public void volt5() {
        System.out.println("5V电压");
    }

    public void vol220() {
        mAdaptee.volt220();
    }
}
