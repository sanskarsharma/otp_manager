package com.dev.sanskar.otpmanager.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dev.sanskar.otpmanager.R;

import mehdi.sakout.aboutpage.AboutPage;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String app_description = "This is an app for facilitating manual sending of OTP messages to a set of contacts (saved as a JSON string). It uses free tier of Nexmo API for sending SMS. \n Nexmo provides SMS sevices from 9:00AM to 9:00PM only to its free tier customers in India. The SMS(s) which will be sent outside this time range will be delivered by Nexmo on the next day. ";
        String str_2 = "\n\nThis app has been developed by Sanskar Sharma. Please visit my Github and LinkedIn (links below) to know more about my projects and experience.";
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.mipmap.ic_launcher)
                .setDescription(app_description+str_2)
                .addEmail("sanskar2996@gmail.com", "Email")
                .addGitHub("sanskarsharma", "More work samples on Github")
                .addWebsite("http://linkedin.com/in/sanskarssh", "LinkedIn Profile")
                .create();

        setContentView(aboutPage);

        setTitle("About");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
