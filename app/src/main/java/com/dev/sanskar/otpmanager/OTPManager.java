package com.dev.sanskar.otpmanager;

import android.app.Application;
import android.content.Context;

/**
 * Created by hadoop on 15/6/18.
 */

public class OTPManager extends Application {

    public static OTPManager instance;


    public OTPManager(){
        instance = this;
    }

    public static OTPManager getInstance() {

        return instance;
    }

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


}
