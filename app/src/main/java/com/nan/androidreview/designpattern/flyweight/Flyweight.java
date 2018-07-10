package com.nan.androidreview.designpattern.flyweight;

public abstract class Flyweight {

    //内部状态，可以共享的信息，存储在对象内部并且不会随环境改变
    private String mIntrinsic;

    //外部状态，不可以共享的信息，随着环境的改变而改变，是对象的索引值
    protected final String mExtrinsic;

    public Flyweight(String extrinsic) {
        mExtrinsic = extrinsic;
    }
}
