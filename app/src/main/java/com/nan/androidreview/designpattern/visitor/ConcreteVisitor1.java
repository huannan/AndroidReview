package com.nan.androidreview.designpattern.visitor;

public class ConcreteVisitor1 implements Visitor {
    @Override
    public void visit(ConcreteElement1 element) {
        System.out.println("ConcreteVisitor1：" + element.mCommonProperty);
    }

    @Override
    public void visit(ConcreteElement2 element) {
        System.out.println("ConcreteVisitor1：" + element.mCommonProperty);
    }
}
