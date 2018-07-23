package com.nan.androidreview.designpattern.interpreter_demo;

import java.util.HashMap;

/**
 * 抽象解释器（AbstractExpression）
 * 具体的解释任务由各个实现类完成，具体的解释器分别由TerminalExpression和NonterminalExpression完成
 */
public abstract class Expression {

    //每个表达式必须有一个解析任务
    public abstract int interpreter(HashMap<String, Integer> var);
}
