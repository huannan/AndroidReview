package com.nan.androidreview.designpattern.factorymethod;

/**
 * 抽象工厂类
 */
public abstract class Factory {

    public abstract <T extends Product> T createProduct(Class<T> clz);

}
