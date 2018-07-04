package com.nan.androidreview.dagger_moudle;

public class OkHttpClient {

    private int cacheSize;

    public OkHttpClient() {

    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
}
