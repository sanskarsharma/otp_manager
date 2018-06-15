package com.dev.sanskar.otpmanager.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.dev.sanskar.otpmanager.OTPManager;

/**
 * Created by hadoop on 15/6/18.
 */

public class Utils {



    public static void saveData(String id, String value) {
        try {

            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            preferences.edit().putString(id, value).commit();

        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }


    }

    public static void saveData(String id, long value) {
        try {

            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            preferences.edit().putLong(id, value).commit();

            Log.i("email", "Save Data : " + id + " : " + value);
        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }


    }

    public static void saveData(String id, int value) {
        try {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);

            preferences.edit().putInt(id, value).commit();
        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }
    }

    public static void saveData(String id, boolean value) {
        try {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);

            preferences.edit().putBoolean(id, value).commit();
        } catch (Exception e) {
            Log.i("Exception", "Exception in save data" + e);
            e.printStackTrace();
        }
    }

    public static boolean getData(String id, boolean value) {

        if (id != null && id.trim().length() > 0) {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getBoolean(id, value);
        }
        return value;
    }

    public static int getData(String id, int value) {

        if (id != null && id.trim().length() > 0) {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getInt(id, value);
        }
        return value;
    }

    public static long getData(String id, long value) {
        if (id != null && id.trim().length() > 0) {
            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getLong(id, value);
        }
        return value;
    }

    public static String getData(String id, String value) {
        if (id != null && id.trim().length() > 0) {

            SharedPreferences preferences = Utils.getPreferencesInstance(null);
            value = preferences.getString(id, value);
        }

        return value;
    }

    public static SharedPreferences getPreferencesInstance(SharedPreferences preferences) {

        if (preferences == null) {

            return PreferenceManager.getDefaultSharedPreferences(OTPManager.getContext());
        }

        return preferences;
    }





}
