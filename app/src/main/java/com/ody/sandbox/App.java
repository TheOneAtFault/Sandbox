package com.ody.sandbox;

import android.app.Application;
import android.content.Context;

import com.mswipe.paycorptransactlib.TransactionFactory;
import com.ody.sandbox.Classes.SharedPref;

public class App extends Application {
    private static TransactionFactory FACTORY;
    private static Context CONTEXT;
    private static boolean Authenticated = false;
    private static boolean DeviceConnected = false;
    private static String DeviceConnected_Name = "";

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPref.init(getApplicationContext());
        CONTEXT = getApplicationContext();
    }

    public static void setTransactionFactory(TransactionFactory factory){
        FACTORY = factory;
    }

    public static TransactionFactory getFactory(){
        return FACTORY;
    }

    public static void setContext(Context context){
        CONTEXT = context;
    }

    public static Context getCONTEXT(){
        return CONTEXT;
    }

}
