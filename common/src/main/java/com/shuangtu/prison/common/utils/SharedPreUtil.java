package com.shuangtu.prison.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.shuangtu.prison.common.constant.Global;

/**
 * SharedPreferences简单数据保存工具类
 */
public class SharedPreUtil {

    // 参数存放的文件名 .xml
    private static final String CONFIG_FILE_NAME = "config";

    public static String getString(Context context, String key, String defValue) {
        // 参数存放的文件名 .xml
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, //
                Context.MODE_PRIVATE);// 文件模式
        return sp.getString(key, defValue);
    }

    public static void saveString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context
                .MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void saveBoolean(Context context, String key, Boolean value) {
        SharedPreferences sp = context.getSharedPreferences(//
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(CONFIG_FILE_NAME, Context
                .MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static Long getLong(Context context, String key, Long defValue) {
        SharedPreferences sp = context.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static void saveLong(Context context, String key, Long value) {
        SharedPreferences sp = context.getSharedPreferences(
                CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void fishSaveString(String key, String value){
        saveString(Global.mContext, key, value);
    }

    public static String fishVuale(String key){
        return getString(Global.mContext, key, "");
    }

    public static String fishVuale(String key, String defValue){
        return getString(Global.mContext, key, defValue);
    }

    public static void fishSaveBoolean(String key, Boolean value){
        saveBoolean(Global.mContext, key, value);
    }

    public static Boolean fishVualeBoolean(String key){
        return getBoolean(Global.mContext, key, false);
    }

    public static Boolean fishVualeBoolean(String key, Boolean defValue){
        return getBoolean(Global.mContext, key, defValue);
    }
}

