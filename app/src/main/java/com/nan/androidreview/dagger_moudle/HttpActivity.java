package com.nan.androidreview.dagger_moudle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nan.androidreview.R;

import javax.inject.Inject;

public class HttpActivity extends AppCompatActivity {

    @Inject
    RetrofitManager mRetrofitManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);

        DaggerHttpActivityComponent.builder()
                .httpActivityModule(new HttpActivityModule(100))
                .build()
                .inject(this);

        System.out.println(mRetrofitManager.getOkHttpClient().getCacheSize());

    }


}