package com.zhaoxuan.crony.crony;

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
            default:
                return "don't support key";
        }
    }
}
