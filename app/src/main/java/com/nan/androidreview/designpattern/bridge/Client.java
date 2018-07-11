package com.nan.androidreview.designpattern.bridge;

public class Client {

    public static void main(String[] args) {

        Implementor i1 = new ConcreteImplementor1();//加糖
        Implementor i2 = new ConcreteImplementor1();//不加糖

        Abstraction a1 = new RefineAbstraction1(i1);//大杯、加糖
        Abstraction a2 = new RefineAbstraction1(i2);//大杯、不加糖
        Abstraction a3 = new RefineAbstraction2(i1);//小杯、加糖
        Abstraction a4 = new RefineAbstraction2(i2);//小杯、不加糖

        a1.operator();
        a2.operator();
        a3.operator();
        a4.operator();
    }

}
