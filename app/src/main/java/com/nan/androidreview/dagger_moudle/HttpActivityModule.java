package com.nan.androidreview.dagger_moudle;

import dagger.Module;
import dagger.Provides;

@Module
public class HttpActivityModule {

    private int mCacheSize;

    public HttpActivityModule(int cacheSize) {
        mCacheSize = cacheSize;
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCacheSize(mCacheSize);
        return okHttpClient;
    }

    @Provides
    RetrofitManager provideRetrofitManager(OkHttpClient client) {
        return new RetrofitManager(client);
    }

}
