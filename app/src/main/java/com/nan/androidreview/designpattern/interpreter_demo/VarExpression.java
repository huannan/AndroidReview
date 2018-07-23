package com.nan.androidreview.designpattern.interpreter_demo;

import java.util.HashMap;

public class VarExpression extends Expression{

    private String mKey;

    public VarExpression(String key) {
        this.mKey = key;
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.mKey);
    }
}
