package com.zhaoxuan.crony;

import android.app.Activity;
import android.app.Application;
import android.content.IntentFilter;
import android.os.Bundle;

public class CronyManager {

    static Application sApplication;
    static String sCurrentActivity;
    static IGetInfo sIGetInfo;

    public static void init(Application application) {
        sApplication = application;

        registerReceiver();
        registerLifecycleCallbacks();
    }

    public static void setIGetInfo(IGetInfo getInfo) {
        sIGetInfo = getInfo;
    }

    private static void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CronyReceiver.ACTION_CLIPBOARD);
        intentFilter.addAction(CronyReceiver.ACTION_CLIPBOARD_PUT);
        intentFilter.addAction(CronyReceiver.ACTION_ACTIVITY);
        intentFilter.addAction(CronyReceiver.ACTION_INFO);
        sApplication.registerReceiver(new CronyReceiver(), intentFilter);
    }

    private static void registerLifecycleCallbacks() {
        sApplication.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                sCurrentActivity = activity.getClass().getCanonicalName();
            }

            @Override
            public void onActivityPaused(Activity activity) {
                sCurrentActivity = null;
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


}
