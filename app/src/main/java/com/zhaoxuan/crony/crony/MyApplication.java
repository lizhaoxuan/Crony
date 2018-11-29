package com.zhaoxuan.crony.crony;

import android.app.Application;

import com.zhaoxuan.crony.CronyManager;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        CronyManager.init(this);
        CronyManager.setIGetInfo(new GetInfo());

    }
}
