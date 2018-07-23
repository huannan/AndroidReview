package com.nan.androidreview.designpattern.interpreter_demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

/**
 * 封装类
 */
public class Calculator {

    private Expression mExpression;

    //通过递归的方式来解析表达式
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
                case '-':
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new SubExpression(left, right));
                    break;
                default:
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
                    break;
            }
        }
        //解析结果赋值给成员变量，通过calculate方法进行运算、得到计算结果
        this.mExpression = stack.pop();
    }

    //计算结果
    public int calculate(HashMap<String, Integer> var) {
        return this.mExpression.interpreter(var);
    }

    //获取表达式
    public static String getExpStr() throws Exception {
        System.out.println("请输入表达式：");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    //为表达式中的运算符号赋值
    public static HashMap<String, Integer> getValues(String expStr) throws Exception {
        HashMap<String, Integer> values = new HashMap<>();

        for (char ch : expStr.toCharArray()) {
            if (ch != '+' && ch != '-') {
                String key = String.valueOf(ch);

                //这里需要防止同样的元素被重复赋值导致值被覆盖
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
