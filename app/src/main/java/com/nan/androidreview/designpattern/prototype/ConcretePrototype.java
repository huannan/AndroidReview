package com.nan.androidreview.designpattern.prototype;

public class ConcretePrototype {

    @Override
    protected Object clone()   {
        ConcretePrototype cp = null;
        try {
            cp = (ConcretePrototype) super.clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cp;
    }

}
