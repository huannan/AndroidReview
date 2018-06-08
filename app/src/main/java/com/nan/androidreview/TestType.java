package com.nan.androidreview;

import java.util.ArrayList;
import java.util.List;

public class TestType<T> {

    T a[];

    public static void main(String[] args) throws Exception {

        List<String> list = new ArrayList<>();
        list.getClass().getMethod("add", Object.class).invoke(list, "abc");

        List<Object> l1 = new ArrayList<String>();
        List l2 = new ArrayList();
        l2 = l1;

    }

    public void print(List<Object> list) {
        Object o = list.get(0);

    }

}
