package com.nan.androidreview.designpattern.interpreter_demo;

import java.util.HashMap;

public class Client {

    public static void main(String[] args) throws Exception {
        //获取表达式
        String expStr = Calculator.getExpStr();
        //为表达式中的元素赋值
        HashMap<String, Integer> values = Calculator.getValues(expStr);
        //构造Calculator，解析表达式
        Calculator calculator = new Calculator(expStr);
        //运算
        int result = calculator.calculate(values);

        System.out.println("计算结果为：" + result);
    }
}
