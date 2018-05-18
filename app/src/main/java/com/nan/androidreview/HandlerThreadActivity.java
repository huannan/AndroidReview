package com.nan.androidreview;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HandlerThreadActivity extends AppCompatActivity {

    private TextView tvMain;

    private HandlerThread mHandlerThread;
    //子线程中的handler
    private Handler mThreadHandler;
    //UI线程中的handler
    private Handler mMainHandler = new Handler();

    //以防退出界面后Handler还在执行
    private boolean isUpdateInfo;
    //用以表示该handler的常量
    private static final int MSG_UPDATE_INFO = 0x110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        tvMain = (TextView) findViewById(R.id.tv_main);

        initThread();
    }


    private void initThread() {
        mHandlerThread = new HandlerThread("check-message-coming");
        mHandlerThread.start();

        mThreadHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                update();//模拟数据更新

                if (isUpdateInfo) {
                    //通过持有 HandlerThread的Looper 的 mThreadHandler去发送消息，触发HandlerThread工作
                    mThreadHandler.sendEmptyMessage(MSG_UPDATE_INFO);
                }
            }
        };

    }

    private void update() {
        try {
            //模拟耗时
            Thread.sleep(2000);
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    String result = "每隔2秒更新一下数据：";
                    result += Math.random();
                    tvMain.setText(result);
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始查询
        isUpdateInfo = true;
        mThreadHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //停止查询
        //以防退出界面后Handler还在执行
        isUpdateInfo = false;
        mThreadHandler.removeMessages(MSG_UPDATE_INFO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mHandlerThread.quit();
    }

}
