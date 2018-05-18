package com.nan.androidreview;

import android.app.IntentService;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //testFragment();
        //testAsyncTask();
        testIntentService();
    }

    private void testIntentService() {

    }


    public void testFragment() {
        Fragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", "value");
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment, "test")
                .commit();
    }

    public void tesetAsyncTask() {
        TestAsyncTask testAsyncTask = new TestAsyncTask();
        testAsyncTask.execute("1", "2", "3");
    }

    class TestAsyncTask extends AsyncTask<String,Integer,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i(TAG, "onPreExecute");
        }

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < strings.length; i++) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                sb.append(strings[i]);
                onProgressUpdate((i + 1) * 100 / strings.length);
            }

            return sb.toString();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.i(TAG, "onProgressUpdate: " + values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i(TAG, "onPostExecute: " + s);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            Log.i(TAG, "onCancelled: " + s);
        }
    }


}