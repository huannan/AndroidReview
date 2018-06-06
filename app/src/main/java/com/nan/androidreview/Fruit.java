package com.nan.androidreview;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Fruit {

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface FruitName {

        String value() default "";

    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface FruitColor {

        enum Color {BLUE, RED, GREEN}

        Color value() default Color.GREEN;

    }

    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface FruitProvider {

        int id() default -1;

        String name() default "";

        String address() default "";
    }

    public class Apple {

        @FruitName("Apple")
        private String appleName;

        @FruitColor(FruitColor.Color.RED)
        private String appleColor;

        @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路89号红富士大厦")
        private String appleProvider;

        public void setAppleColor(String appleColor) {
            this.appleColor = appleColor;
        }

        public String getAppleColor() {
            return appleColor;
        }

        public void setAppleName(String appleName) {
            this.appleName = appleName;
        }

        public String getAppleName() {
            return appleName;
        }

        public void setAppleProvider(String appleProvider) {
            this.appleProvider = appleProvider;
        }

        public String getAppleProvider() {
            return appleProvider;
        }

        public void displayName() {
            System.out.println("水果的名字是：苹果");
        }
    }

    public static class FruitInfoUtil {
        public void getFruitInfo(Class<?> clazz) {

            String strFruitName = " 水果名称：";
            String strFruitColor = " 水果颜色：";
            String strFruitProvicer = "供应商信息：";

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(FruitName.class)) {
                    FruitName fruitName = (FruitName) field.getAnnotation(FruitName.class);
                    strFruitName = strFruitName + fruitName.value();
                    System.out.println(strFruitName);
                } else if (field.isAnnotationPresent(FruitColor.class)) {
                    FruitColor fruitColor = (FruitColor) field.getAnnotation(FruitColor.class);
                    strFruitColor = strFruitColor + fruitColor.value().toString();
                    System.out.println(strFruitColor);
                } else if (field.isAnnotationPresent(FruitProvider.class)) {
                    FruitProvider fruitProvider = (FruitProvider) field.getAnnotation(FruitProvider.class);
                    strFruitProvicer = " 供应商编号：" + fruitProvider.id() + " 供应商名称：" + fruitProvider.name() + " 供应商地址：" + fruitProvider.address();
                    System.out.println(strFruitProvicer);
                }
            }
        }
    }

    public static void main(String[] args) {
        FruitInfoUtil util = new FruitInfoUtil();
        util.getFruitInfo(Apple.class);
    }

}
