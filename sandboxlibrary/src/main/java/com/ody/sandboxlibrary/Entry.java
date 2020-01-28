package com.ody.sandboxlibrary;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

public class Entry {
    public Entry(){

    }

    public static String access(Activity activity){
        AppCompatActivity compatActivity = (AppCompatActivity) activity;
        //extract activity code here
        return "access entered";
    }
}
