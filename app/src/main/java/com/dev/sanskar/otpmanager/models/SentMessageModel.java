package com.dev.sanskar.otpmanager.models;

/**
 * Created by hadoop on 15/6/18.
 */

public class SentMessageModel {

    private ContactModel contact;
    private String sent_timestamp;
    private String sent_message;
    private String api_response;
    private String otp_code;

    public SentMessageModel(){

    }

    public SentMessageModel(ContactModel contact, String sent_timestamp, String sent_message, String api_response, String otp_code) {
        this.contact = contact;
        this.sent_timestamp = sent_timestamp;
        this.sent_message = sent_message;
        this.api_response = api_response;
        this.otp_code = otp_code;
    }

    public String getOtp_code() {
        return otp_code;
    }

    public void setOtp_code(String otp_code) {
        this.otp_code = otp_code;
    }

    public ContactModel getContact() {
        return contact;
    }

    public void setContact(ContactModel contact) {
        this.contact = contact;
    }

    public String getSent_timestamp() {
        return sent_timestamp;
    }

    public void setSent_timestamp(String sent_timestamp) {
        this.sent_timestamp = sent_timestamp;
    }

    public String getSent_message() {
        return sent_message;
    }

    public void setSent_message(String sent_message) {
        this.sent_message = sent_message;
    }

    public String getApi_response() {
        return api_response;
    }

    public void setApi_response(String api_response) {
        this.api_response = api_response;
    }
}
