package com.nan.androidreview;

import javax.xml.transform.Source;

public class ExceptionDemo {

    public static void main(String[] args) {
        test4();
    }

    public static void test1() {

        try {
            //System.exit(0);
            int i = 1/0;
        } catch (Exception e) {
            System.exit(0);
        }finally {
            System.out.println("finally");
        }

    }


    public static int test2() {
        int a = 0;
        try {
            return a = 1;
        } catch (Exception e) {
            System.exit(0);
        }finally {
            a = 2;
            System.out.println("finally");
        }
        return 0;
    }

    public static int test3() {
        int a = 0;
        try {
            return a = 1;
        } catch (Exception e) {

        }finally {
            a = 2;
            System.out.println("finally");
            return a;
        }
    }

    public static void test4() {
        try {
            int a = 1/0;
        } catch (Exception e) {
            System.out.println("catch");
            int b = 1 / 0;
        }finally {
            System.out.println("finally");
            return;
        }
    }

}
