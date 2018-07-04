package com.nan.androidreview.dagger;

import javax.inject.Inject;

public class Factory {

    Product mProduct;

    @Inject
    public Factory(Product product) {
        mProduct = product;
    }
}
