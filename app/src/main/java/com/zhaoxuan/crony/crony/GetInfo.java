package com.zhaoxuan.crony.crony;

import android.util.Log;

import com.zhaoxuan.crony.IGetInfo;

public class GetInfo implements IGetInfo {

    @Override
    public String getInfo(String key) {
        switch (key) {
            case "":
                return "userID:99999 \n deviceId:azxccasdasd";
            case "user":
                return "userID:99999 ";
            case "info":
                return "userID:99999 \n deviceId:azxccasdasd";
            case "c1":
                //执行某段自定义代码
                Log.d("TAG","");
                return "ok";
            default:
                return "don't support key";
        }
    }
}
