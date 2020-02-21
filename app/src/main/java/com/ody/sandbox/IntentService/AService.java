package com.ody.sandbox.IntentService;

import android.app.IntentService;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.mswipe.paycorptransactlib.FactoryDevices;
import com.mswipe.paycorptransactlib.ServiceConfigurationEnum;
import com.mswipe.paycorptransactlib.TransactionFactory;
import com.ody.sandbox.App;
import com.ody.sandbox.Classes.Authentication;
import com.ody.sandbox.MainActivity;

import java.util.ArrayList;

public class AService extends IntentService {
    private String userName;
    private String userPass;
    private int DEVICES = 001;
    private ArrayList<String> deviceList = new ArrayList<>();
    private SharedPreferences mPrefs;
    private TransactionFactory factory;
    private FactoryDevices _factoryDevices;
    private boolean _connected = false;
    private BluetoothDevice _currentDevice;
    private String deviceName;

    public AService() {
        super("AService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Context context = App.getCONTEXT();
        String message = intent.getStringExtra("message");
        intent.setAction("com.ody.ZipZap.ODY_ZIPZAP");
        //SystemClock.sleep(3000);

        String a = Auth(intent);

        String echoMessage = "Form external app:  " + a;
        //LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra("service-test", echoMessage));
        Intent intentbb = new Intent("com.ody.ZipZap.ODY_ZIPZAP");
        intentbb.putExtra("service-test", "service-test");
        intentbb.putExtra("message", echoMessage);
        context.sendBroadcast(intentbb);
    }

    private String Auth(Intent intent){
        //Bundle bundle = intent.getExtras();
        //run auth

        String USER_NAME = intent.getStringExtra("user");
        String USER_PASS = intent.getStringExtra("pass");


        factory = App.getFactory();
        Context context = App.getCONTEXT();

        Authentication authentication = new Authentication(USER_NAME, USER_PASS);
        authentication.attempt(context, factory);

        return authentication.getAuthError();
    }
}
