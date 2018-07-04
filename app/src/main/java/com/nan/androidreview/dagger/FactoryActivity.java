package com.nan.androidreview.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nan.androidreview.R;

import javax.inject.Inject;

public class FactoryActivity extends AppCompatActivity {

    @Inject
    Factory mFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);

        DaggerFactoryActivityComponent.create().inject(this);

        System.out.println(this.mFactory.mProduct.hashCode() + "");

    }


}