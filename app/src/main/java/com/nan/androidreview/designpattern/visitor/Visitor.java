package com.nan.androidreview.designpattern.visitor;

public interface Visitor {
    void visit(ConcreteElement1 element);
    void visit(ConcreteElement2 element);
}
