package com.nan.androidreview.designpattern.visitor;

public class ConcreteVisitor2 implements Visitor {
    @Override
    public void visit(ConcreteElement1 element) {
        System.out.println("ConcreteVisitor2：" + element.mProperty1);
    }

    @Override
    public void visit(ConcreteElement2 element) {
        System.out.println("ConcreteVisitor2：" + element.mProperty2);
    }
}
