package com.nan.androidreview.designpattern.iterator;

public class Client {

    public static void main(String[] args) {
        Aggregate agg = new ConcreteAggregate();
        agg.add("a");
        agg.add("b");
        agg.add("c");

        Iterator iterator = agg.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
