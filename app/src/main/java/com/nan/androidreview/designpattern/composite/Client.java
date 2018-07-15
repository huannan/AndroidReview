package com.nan.androidreview.designpattern.composite;

public class Client {

    public static void main(String[] args) {
        Component c1 = new Composite("C盘");
        Component c2 = new Composite("文件夹A");
        Component c3 = new Leaf("文件A");
        Component c4 = new Leaf("文件B");

        c1.addChildren(c2);
        c1.addChildren(c3);
        c2.addChildren(c4);
    }

}
