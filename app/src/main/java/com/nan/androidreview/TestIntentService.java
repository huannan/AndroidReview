package com.nan.androidreview;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class TestIntentService extends IntentService {

    public TestIntentService() {
        super("TestIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
