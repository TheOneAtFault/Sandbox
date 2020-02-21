package com.ody.sandbox.Classes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mswipe.paycorptransactlib.FactoryDevices;
import com.mswipe.paycorptransactlib.LoggedInUserResponse;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class SharedPref {
    private static SharedPreferences mSharedPref;
    public static final String USER_NAME = "USERNAME";
    public static final String USER_PASS = "USERPASS";
    public static final String IS_SELECT = "IS_SELECT";
    public static final String AUTH = "AUTH";
    public static final String USER_RESPONSE = "USER_RESPONSE";
    public static final String FACTORY_DEVICES = "FACTORY_DEVICES";

    private SharedPref() {

    }

    public static void init(Context context) {
        if (mSharedPref == null)
            mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }

    public static String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public static boolean read(String key, boolean defValue) {
        return mSharedPref.getBoolean(key, defValue);
    }

    public static Integer read(String key, int defValue) {
        return mSharedPref.getInt(key, defValue);
    }

    public static LoggedInUserResponse readUserResponse(String key) {
        Gson gson = new Gson();
        String json = mSharedPref.getString(key, "");
        LoggedInUserResponse obj = gson.fromJson(json, LoggedInUserResponse.class);
        return obj;
    }

    public static FactoryDevices readTFactory(String key) {
        Gson gson = new Gson();
        String json = mSharedPref.getString(key, "");
        FactoryDevices obj = gson.fromJson(json, FactoryDevices.class);
        return obj;
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public static void write(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putInt(key, value).commit();
    }

    public static void write(String key, Object value) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        prefsEditor.putString(key, json).commit();
    }

    public static void write(String key, LoggedInUserResponse userProfile) {
        try {
            SharedPreferences.Editor prefEditor = mSharedPref.edit();
            Gson gson = new Gson();
            String json = gson.toJson(userProfile);
            prefEditor.putString(key, json).commit();
        }catch (Exception e){
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            String s = writer.toString();
        }
    }

    public static void write(String key, FactoryDevices value) {
        try {
            SharedPreferences.Editor prefEditor = mSharedPref.edit();
            Gson gson = new Gson();
            String json = gson.toJson(value);
            prefEditor.putString(key, json).commit();
        }catch (Exception e){
            Writer writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            String s = writer.toString();
        }
    }
}
