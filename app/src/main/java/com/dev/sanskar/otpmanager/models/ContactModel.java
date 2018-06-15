package com.dev.sanskar.otpmanager.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hadoop on 15/6/18.
 */

public class ContactModel implements Parcelable{

    private String name;
    private String number;

    private ContactModel(){
        // making default const private
    }

    public ContactModel(String name, String number){
        this.name= name;
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }



    protected ContactModel(Parcel in) {
        name = in.readString();
        number = in.readString();
    }

    public static final Creator<ContactModel> CREATOR = new Creator<ContactModel>() {
        @Override
        public ContactModel createFromParcel(Parcel in) {
            return new ContactModel(in);
        }

        @Override
        public ContactModel[] newArray(int size) {
            return new ContactModel[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(number);
    }
}
