package com.nan.androidreview.designpattern.interpreter_demo;

public class Client {

    public static void main(String[] args) throws Exception {
        String expStr = Calculator.getExpStr();
        Calculator calculator = new Calculator(expStr);
        int result = calculator.calculate(Calculator.getValues(expStr));

        System.out.println(result);
    }
}
