package com.nan.androidreview.designpattern.visitor;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        //这里的List充当了UML中的ObjectStruture——结构对象，又叫元素产生者，一般容纳在多个不同类、不同接口的容器，如List、Set、Map等。
        //在项目中，一般很少抽象出这个角色。
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
