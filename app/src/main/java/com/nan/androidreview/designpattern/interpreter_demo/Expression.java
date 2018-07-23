package com.nan.androidreview.designpattern.interpreter_demo;

import java.util.HashMap;

public abstract class Expression {

    public abstract int interpreter(HashMap<String, Integer> var);
}
