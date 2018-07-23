package com.nan.androidreview.designpattern.interpreter_demo;

/**
 * 非终结符表达式（NonterminalExpression）
 * 非终结符表达式又叫运算符号、做非终结符号
 * 运算符号就是加减符号，需要我们编写算法进行处理，每个运算符号都要对应处理单元，否则公式无法运行
 * 非终结符表达式根据逻辑的复杂程度而增加，原则上每个文法规则都对应一个非终结符表达式
 */
public abstract class SymbolExpression extends Expression {

    //每个非终结符表达式都会对其他表达式产生依赖
    protected Expression mLeft;
    protected Expression mRight;

    public SymbolExpression(Expression left, Expression right) {
        this.mLeft = left;
        this.mRight = right;
    }
}
