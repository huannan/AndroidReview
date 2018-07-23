package com.nan.androidreview.designpattern.visitor;

public class ConcreteElement1 extends Element {

    public String mProperty1;

    public ConcreteElement1(String commonProperty, String property) {
        super(commonProperty);
        this.mProperty1 = property;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
