package com.nan.androidreview.designpattern.interpreter_demo;

import java.util.HashMap;

/**
 * 终结符表达式（TerminalExpression）
 * 终结符表达式又叫做运算元素、运算变量，也叫做终结符号，运算元素就是指a、b、c等符号，需要具体赋值的对象
 * 终结符：这些元素除了需要赋值外，不需要做任何处理。所有运算元素都对应一个具体的业务参数，这是语法中最小的单元逻辑，不可再拆分
 * 通常一个解释器模式中只有一个终结符表达式，但有多个实例，对应不同的终结符。
 */
public class VarExpression extends Expression {

    private String mKey;

    public VarExpression(String key) {
        this.mKey = key;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.mKey);
    }
}
