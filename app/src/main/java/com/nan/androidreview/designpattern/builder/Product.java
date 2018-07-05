package com.nan.androidreview.designpattern.builder;

public class Product {

    private String mPartA;
    private String mPartB;
    private String mPartC;

    public static class Builder{

        Product mProduct;

        public Builder() {
            this.mProduct = new Product();
        }

        public Builder buildPartA(String partA) {
            this.mProduct.mPartA = partA;
            return this;
        }

        public Builder buildPartB(String partB) {
            this.mProduct.mPartB = partB;
            return this;
        }

        public Builder buildPartC(String partC) {
            this.mProduct.mPartC = partC;
            return this;
        }

        public Product build() {
            return mProduct;
        }
    }


    public static void main(String[] args) {
        Product product = new Product.Builder()
                .buildPartA("a")
                .buildPartB("b")
                .buildPartC("c")
                .build();
    }

}
