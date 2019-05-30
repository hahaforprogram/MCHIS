package com.haha.mchis;
import android.app.Application;

public class MyApplication extends Application {
    private static final String TAG = "Myapplication";
    private static MyApplication instance = null;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            synchronized (MyApplication.class) {
                if (instance == null) {
                    instance = new MyApplication();
                }
            }
        }
        return instance;
    }


}
