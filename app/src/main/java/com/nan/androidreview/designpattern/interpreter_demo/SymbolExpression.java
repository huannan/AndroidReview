package com.nan.androidreview.designpattern.interpreter_demo;

public abstract class SymbolExpression extends Expression {

    protected Expression mLeft;
    protected Expression mRight;

    public SymbolExpression(Expression left, Expression right) {
        this.mLeft = left;
        this.mRight = right;
    }
}
