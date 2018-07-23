package com.nan.androidreview.designpattern.visitor;

public abstract class Element {

    public String mCommonProperty;

    public Element(String commonProperty) {
        mCommonProperty = commonProperty;
    }

    public abstract void accept(Visitor visitor);
}
