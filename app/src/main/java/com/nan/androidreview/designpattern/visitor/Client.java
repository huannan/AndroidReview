package com.nan.androidreview.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        List<Element> elements = new ArrayList<>();
        elements.add(new ConcreteElement1("A", "1"));
        elements.add(new ConcreteElement1("B", "2"));
        elements.add(new ConcreteElement2("C", "3"));
        elements.add(new ConcreteElement2("D", "4"));

        Visitor visitor1 = new ConcreteVisitor1();
        Visitor visitor2 = new ConcreteVisitor2();

        for (Element e : elements) {
            e.accept(visitor1);
        }

        System.out.println("-----------");

        for (Element e : elements) {
            e.accept(visitor2);
        }
    }
}
