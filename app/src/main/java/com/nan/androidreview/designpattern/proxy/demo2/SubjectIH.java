package com.nan.androidreview.designpattern.proxy.demo2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectIH implements InvocationHandler {

    private Object mTarget;

    public SubjectIH(Object target) {
        mTarget = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {

        before();

        Object res = method.invoke(this.mTarget, args);

        after();

        if ("request".equals(method.getName())) {
            System.out.println("request！！");
        }

        return res;
    }

    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }
}
