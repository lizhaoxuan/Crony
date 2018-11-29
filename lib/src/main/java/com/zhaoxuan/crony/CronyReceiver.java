package com.zhaoxuan.crony;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import static android.content.Context.CLIPBOARD_SERVICE;
import static com.zhaoxuan.crony.CronyManager.sApplication;

public class CronyReceiver extends BroadcastReceiver {


    private static final String RESULT_CODE_FAILED = "\n0\n";
    private static final String RESULT_CODE_SUCCESS = "\n1\n";
    private static final String RESULT_CODE_NO_REGISTER_IGETINFO = "\n2\n";

    //获取剪切板
    public static final String ACTION_CLIPBOARD = "com.zhaoxuan.crony.action_clipboard";
    //剪切板赋值
    public static final String ACTION_CLIPBOARD_PUT = "com.zhaoxuan.crony.action_clipboard_put";
    //顶部Activity
    public static final String ACTION_ACTIVITY = "com.zhaoxuan.crony.action_activity";
    //info信息
    public static final String ACTION_INFO = "com.zhaoxuan.crony.action_info";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) {
            return;
        }
        switch (intent.getAction()) {
            case ACTION_CLIPBOARD:
                getClipboard();
                break;
            case ACTION_CLIPBOARD_PUT:
                putClipboard(intent);
                break;
            case ACTION_ACTIVITY:
                getTopActivity();
                break;
            case ACTION_INFO:
                getInfo(intent);
                break;
        }
        setResultCode(Activity.RESULT_OK);
    }

    private void getClipboard() {
        ClipboardManager cm = (ClipboardManager) sApplication.getSystemService(CLIPBOARD_SERVICE);
        ClipData data = cm.getPrimaryClip();
        if (data != null) {
            ClipData.Item item = data.getItemAt(0);
            String content = item.getText().toString();
            setResultData(RESULT_CODE_SUCCESS + content);
        } else {
            setResultData(RESULT_CODE_FAILED);
        }
    }

    private void putClipboard(Intent intent) {
        String content = intent.getStringExtra("content");
        ClipboardManager clipboard = (ClipboardManager)
                sApplication.getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Crony", content);
        clipboard.setPrimaryClip(clip);
        setResultData(RESULT_CODE_SUCCESS);
    }

    private void getTopActivity() {
        ActivityManager mAm = (ActivityManager) sApplication.getSystemService(Context.ACTIVITY_SERVICE);
        String activityName = mAm.getRunningTasks(1).get(0).topActivity.getClassName();
        setResultData(RESULT_CODE_SUCCESS + activityName);
    }

    private void getInfo(Intent intent) {
        if (CronyManager.sIGetInfo != null) {
            String key = intent.getStringExtra("key");
            if ("default".equals(key)) {
                key = "";
            }
            setResultData(RESULT_CODE_SUCCESS + CronyManager.sIGetInfo.getInfo(key));
        } else {
            setResultData(RESULT_CODE_NO_REGISTER_IGETINFO);
        }

    }
}
