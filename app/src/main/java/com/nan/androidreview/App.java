package com.nan.androidreview;

import android.app.Application;
import android.os.Build;

import com.alibaba.android.arouter.launcher.ARouter;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();
        if (BuildConfig.DEBUG) {
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {

            }
        });
    }
}
