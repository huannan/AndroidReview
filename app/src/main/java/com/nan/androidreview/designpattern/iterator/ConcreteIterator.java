package com.nan.androidreview.designpattern.iterator;

import java.util.Vector;

/**
 * 具体迭代器
 */
public class ConcreteIterator implements Iterator {

    private Vector mVector;
    //定义当前游标
    private int mCursor = 0;

    public ConcreteIterator(Vector vector) {
        this.mVector = vector;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return this.mVector.get(this.mCursor++);
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        if (this.mCursor == this.mVector.size()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean remove() {
        this.mVector.remove(this.mCursor);
        return true;
    }
}
