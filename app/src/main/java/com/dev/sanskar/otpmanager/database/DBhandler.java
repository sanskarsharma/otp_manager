package com.dev.sanskar.otpmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dev.sanskar.otpmanager.models.ContactModel;
import com.dev.sanskar.otpmanager.models.SentMessageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hadoop on 15/6/18.
 */

public class DBhandler extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "dataDB";

    private static final String TABLE_DATA = "datatable1";

    private static final String KEY_SENT_TIMESTAMP = "timestamp";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_SENT_MESSAGE = "sentmsg";
    private static final String KEY_API_RESPONSE = "apiresponse";
    private static final String KEY_OTP_CODE = "otpcode";


    public DBhandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATA_TABLE = "CREATE TABLE " + TABLE_DATA + "("
                + KEY_SENT_TIMESTAMP + " TEXT PRIMARY KEY, " + KEY_CONTACT +" TEXT, "+ KEY_SENT_MESSAGE +" TEXT, "+ KEY_API_RESPONSE +" TEXT, "+ KEY_OTP_CODE +" TEXT "+ ")";
        db.execSQL(CREATE_DATA_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);

        // Create tables again
        onCreate(db);
    }



    public void addData(SentMessageModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_SENT_TIMESTAMP, dataModel.getSent_timestamp());
        values.put(KEY_CONTACT, dataModel.getContact().toString());
        values.put(KEY_SENT_MESSAGE, dataModel.getSent_message());
        values.put(KEY_API_RESPONSE, dataModel.getApi_response());
        values.put(KEY_OTP_CODE, dataModel.getOtp_code());

        // Inserting Row
        db.insert(TABLE_DATA, null, values);
        db.close(); // Closing database connection
    }

    public List<SentMessageModel> getAllEntries() {
        List<SentMessageModel> datalist = new ArrayList<SentMessageModel>();

        String selectQuery = "SELECT  * FROM " + TABLE_DATA ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SentMessageModel datu = new SentMessageModel();
                datu.setSent_timestamp(cursor.getString(0));

                Log.e("LLLLLLLLLLLLL",cursor.getString(1));
                datu.setContact(ContactModel.fromString(cursor.getString(1)));

                datu.setSent_message(cursor.getString(2));
                datu.setApi_response(cursor.getString(3));
                datu.setOtp_code(cursor.getString(4));

                // Adding note to list
                datalist.add(datu);
            } while (cursor.moveToNext());
        }

        return datalist;
    }

    public void deleteData(SentMessageModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DATA, KEY_SENT_TIMESTAMP + " = ?",
                new String[]{String.valueOf(dataModel.getSent_timestamp())});
        db.close();
    }



    // unused function
    public int getEntryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DATA ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }






}
