package com.ody.sandbox.Classes;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.mswipe.paycorptransactlib.FactoryDevices;
import com.mswipe.paycorptransactlib.LoggedInUserResponse;
import com.mswipe.paycorptransactlib.ServiceConfigurationEnum;
import com.mswipe.paycorptransactlib.TransactionFactory;
import com.mswipe.paycorptransactlib.UserAuthRequest;
import com.ody.sandbox.App;

public class Authentication extends AppCompatActivity {
    private String USER_NAME, USER_PASSWORD;
    private boolean authenticated = false;
    private LoggedInUserResponse _userProfile;
    private TransactionFactory factory;
    private FactoryDevices _devices;
    private ServiceConfigurationEnum ENVIRONMENT;
    private SharedPreferences mPrefs;

    public Authentication(String u_n, String u_p) {
        this.USER_NAME = u_n;
        this.USER_PASSWORD = u_p;
    }

    public Authentication() {
        this.USER_NAME = SharedPref.read(SharedPref.USER_NAME, "");
        this.USER_PASSWORD = SharedPref.read(SharedPref.USER_PASS, "");
        this._userProfile = SharedPref.readUserResponse(SharedPref.USER_RESPONSE);
    }

    public boolean checkAuthState() {
        if (!_userProfile.IsSuccessful) {
            return false;
        } else {
            return true;
        }
    }

    public String getAuthError() {
        return _userProfile.Message;
    }

    public void attempt(Context context, TransactionFactory data) {
        UserAuthRequest request = new UserAuthRequest();
        request.UserName = USER_NAME;
        request.Password = USER_PASSWORD;

        factory = App.getFactory();

        _userProfile = factory.authenticate(request);

        storeSharedPreferences(
                USER_NAME,
                USER_PASSWORD,
                _userProfile,
                factory.getExternalDevices());


    }

    //helpers
    private void setEnvironment(String flag) {
        switch (flag) {
            case "QA":
                ENVIRONMENT = ServiceConfigurationEnum.QA;
                break;
            case "UAT":
                ENVIRONMENT = ServiceConfigurationEnum.UAT;
                break;
            case "PROD":
                ENVIRONMENT = ServiceConfigurationEnum.PROD;
                break;
        }
    }

    private void storeSharedPreferences(String userName, String userPass, LoggedInUserResponse response, FactoryDevices tFactory) {
        SharedPref.write(SharedPref.USER_NAME, userName);
        SharedPref.write(SharedPref.USER_PASS, userPass);
        SharedPref.write(SharedPref.USER_RESPONSE, _userProfile);
        SharedPref.write(SharedPref.FACTORY_DEVICES, tFactory);
    }
}
