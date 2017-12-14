package com.example.anonymous.jpush;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Anonymous on 2017/12/14.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
