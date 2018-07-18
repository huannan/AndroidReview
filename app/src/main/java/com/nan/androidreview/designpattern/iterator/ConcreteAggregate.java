package com.nan.androidreview.designpattern.iterator;

import java.util.Vector;

/**
 * 具体容器
 */
public class ConcreteAggregate implements Aggregate {

    private Vector mVector = new Vector();

    @Override
    public void add(Object o) {
        this.mVector.add(o);
    }

    @Override
    public void remove(Object o) {
        this.mVector.remove(o);
    }

    @Override
    public Iterator iterator() {
        return new ConcreteIterator(this.mVector);
    }
}
