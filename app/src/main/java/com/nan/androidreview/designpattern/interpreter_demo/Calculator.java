package com.nan.androidreview.designpattern.interpreter_demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Calculator {

    private Expression mExpression;

    public Calculator(String expStr) {
        Stack<Expression> stack = new Stack<>();
        char[] charArray = expStr.toCharArray();

        Expression left;
        Expression right;

        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case '+':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new AddExpression(left, right));
                    break;
                default:
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
                    break;
            }
        }

        this.mExpression = stack.pop();
    }

    public int calculate(HashMap<String, Integer> var) {
        return this.mExpression.interpreter(var);
    }

    public static String getExpStr() throws Exception {
        System.out.println("请输入表达式：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static HashMap<String, Integer> getValues(String expStr) throws Exception {
        HashMap<String, Integer> values = new HashMap<>();

        for (char ch : expStr.toCharArray()) {
            if (ch != '+' && ch != '-') {
                String key = String.valueOf(ch);
                if (!values.containsKey(key)) {
                    System.out.println("请输入" + key + "的值：");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    Integer value = Integer.valueOf(reader.readLine());
                    values.put(key, value);
                }
            }
        }

        return values;
    }

}
