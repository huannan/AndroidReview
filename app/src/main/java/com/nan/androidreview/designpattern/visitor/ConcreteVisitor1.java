package com.nan.androidreview.designpattern.visitor;

/**
 * 具体访问者
 * 影响访问者访问到一个类后该怎么干，要做什么事情。
 */
public class ConcreteVisitor1 implements Visitor {
    @Override
    public void visit(ConcreteElement1 element) {
        //注：这里的访问逻辑需要根据具体业务来定
        //一般来说，不同访问者对同一种元素类型有不同的访问方式
        System.out.println("ConcreteVisitor1：" + element.mCommonProperty);
    }

    @Override
    public void visit(ConcreteElement2 element) {
        System.out.println("ConcreteVisitor1：" + element.mCommonProperty);
    }
}
