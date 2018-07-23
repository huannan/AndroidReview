package com.nan.androidreview.designpattern.visitor;

public class ConcreteElement2 extends Element {

    public String mProperty2;

    public ConcreteElement2(String commonProperty, String property) {
        super(commonProperty);
        this.mProperty2 = property;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
