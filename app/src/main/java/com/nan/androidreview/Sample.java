package com.nan.androidreview;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sample {

    @Target(ElementType.TYPE)
    public @interface Table {
        String tableName() default "name";
    }

    @Documented
    @Table(tableName = "c1")
    public @interface NoDBColumn {

    }

    //因为类被new出来之后是存放在堆中的，所有成员变量全部存储于堆中（包括基本数据类型，引用和引用的对象实体）
    @NoDBColumn
    int i1 = 0;
    Sample s1 = new Sample();

    public void method() {
        //局部变量和引用变量都是存在于栈中，但引用变量指向的对象是存在于堆中
        int i2 = 1;
        Sample s2 = new Sample();
    }

    public static void main(String[] args) {
        //局部变量和引用变量都是存在于栈中，但引用变量指向的对象是存在于堆中
        Sample s3 = new Sample();
    }


}


/**

* 局部变量i2和引用变量s2都是存在于栈中，但s2指向的对象是存在于堆上的。
* 引用变量s3存在栈上，指向的对象实体存放在堆上，包括这个对象的所有成员变量i1和s1，而它自己存在于栈中。

**/