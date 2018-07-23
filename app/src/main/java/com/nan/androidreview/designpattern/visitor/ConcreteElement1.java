package com.nan.androidreview.designpattern.visitor;

/**
 * 具体元素
 * 实现accept方法，通常是visitor.visit(this)，基本上都形成了一种模式了
 */
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
