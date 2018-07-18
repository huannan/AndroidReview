package com.nan.androidreview.designpattern.iterator;

/**
 * 抽象容器：容器角色负责提供容器的基本操作、创建具体迭代器角色的接口，在Java中一般是iterator()方法。
 */
public interface Aggregate {
    void add(Object o);
    void remove(Object o);
    Iterator iterator();
}
