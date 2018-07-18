package com.nan.androidreview.designpattern.iterator;

/**
 * 抽象迭代器：抽象迭代器负责定义访问和遍历元素的接口
 */
public interface Iterator {
    //遍历到下一个元素
    Object next();
    //是否已经遍历到尾部
    boolean hasNext();
    //删除当前指向的元素
    boolean remove();
}
