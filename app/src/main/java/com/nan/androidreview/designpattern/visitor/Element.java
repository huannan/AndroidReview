package com.nan.androidreview.designpattern.visitor;

/**
 * 抽象元素
 * 接口或者抽象类，声明接受哪一类访问者访问，程序上是通过accept方法中的参数来定义的
 */
public abstract class Element {

    public String mCommonProperty;

    public Element(String commonProperty) {
        mCommonProperty = commonProperty;
    }

    public abstract void accept(Visitor visitor);
}
