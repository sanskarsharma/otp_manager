package com.dev.sanskar.otpmanager.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.dev.sanskar.otpmanager.OTPManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/**
 * Created by hadoop on 15/6/18.
 */

public class Utils {


    public static final String KEY_JSON_CONTACTS = "keyjsoncontacts";
    public static String JSON_CONTACTS =
            "[" +
                    "{ 'name' : 'Sanskar Sharma', 'number': '8349404499'}," +
                    "{ 'name' : 'Sanskar 2', 'number': '7987977036'}," +
                    "{ 'name' : 'Kisan Network', 'number': '9971792703'}" +
                    "]";

//    public void init_json_contacts(){
//
//        saveData(KEY_JSON_CONTACTS, JSON_CONTACTS);
//
//    }

    public static String generateOTP(){
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return  n + "";
    }


    public static String getDateandtime(long timestamp) {
        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getTimeZone("India/Kolkata");
            calendar.setTimeInMillis(timestamp);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdfDate = new SimpleDateFormat("EEE, d MMM, ''yy");
            SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm a");

            java.util.Date currenTimeZone = (Date) calendar.getTime();
            return sdfDate.format(currenTimeZone)+"-"+sdfTime.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }



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
