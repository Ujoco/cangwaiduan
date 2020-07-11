package com.shuangtu.prison.home.socket;

import com.google.gson.Gson;

import java.util.HashMap;

public class QSocketCmd {

    private static final Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }

    public static String getHeartbeat() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("message", System.currentTimeMillis());
        params.put("type", "heartbeat");
        return gson.toJson(params);
    }

    public static String getOvertimeConfig() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("message", System.currentTimeMillis());
        params.put("type", "type");
        return gson.toJson(params);
    }

}
