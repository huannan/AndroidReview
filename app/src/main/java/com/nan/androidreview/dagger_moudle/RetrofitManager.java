package com.nan.androidreview.dagger_moudle;

public class RetrofitManager {

    private OkHttpClient mOkHttpClient;

    public RetrofitManager(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
