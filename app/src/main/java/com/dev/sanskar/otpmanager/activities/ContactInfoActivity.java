package com.dev.sanskar.otpmanager.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.internal.MDButton;
import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dev.sanskar.otpmanager.R;
import com.dev.sanskar.otpmanager.database.DBhandler;
import com.dev.sanskar.otpmanager.fragments.SentMessagesFragment;
import com.dev.sanskar.otpmanager.models.ContactModel;
import com.dev.sanskar.otpmanager.models.SentMessageModel;
import com.dev.sanskar.otpmanager.utils.RequestQueueSingleton;
import com.dev.sanskar.otpmanager.utils.Utils;
import com.github.ppamorim.dragger.DraggerActivity;
import com.github.ppamorim.dragger.DraggerPosition;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

public class ContactInfoActivity extends AppCompatActivity {

    TextView name, number;
    FloatingActionButton fab_sms;

    MaterialDialog progress_dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);


        setTitle("Contact Details");
        name = (TextView) findViewById(R.id.tv_info_name);
        number = (TextView) findViewById(R.id.tv_info_number);
        fab_sms = (FloatingActionButton) findViewById(R.id.fab_sms) ;


        Intent intent = getIntent();
        final ContactModel model = (ContactModel) intent.getParcelableExtra("model");
        name.setText(model.getName());
        number.setText(model.getNumber());

        progress_dialog = new MaterialDialog.Builder(this)
                .title("Sending ...")
                .content("OTP code is being sent")
                .progress(true, 0).build();


        fab_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialDialog dialog = new MaterialDialog.Builder(ContactInfoActivity.this)
                        .title("Compose OTP Message")
                        .positiveText("Send")
                        .negativeText("Cancel")
                        .customView(R.layout.compose_dialog_layout,true)
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                View view = dialog.getCustomView();
                                EditText otp = view.findViewById(R.id.editText_OTP);
                                if(otp.getText().toString().trim().equals("")){
                                    otp.setError("OTP cannot be empty");
                                    Toasty.info(getApplicationContext(),"OTP cannot be empty, Try again.", Toast.LENGTH_LONG,false).show();
                                    return;
                                }

                                EditText extra = view.findViewById(R.id.editText_ExtraMSG);

                                String msg = "Your OTP is : "+ otp.getText().toString()+ "\n";
                                String extra_msg = extra.getText().toString();
                                msg = msg + extra_msg;

                                progress_dialog.show();
                                send_request(model, msg, otp.getText().toString() );
                            }
                        })
                        .show();

                View view = dialog.getCustomView();
                TextView tv_to = view.findViewById(R.id.label_to);
                tv_to.setText("To : "+ model.getName()+ " ("+model.getNumber()+")");
                EditText otp = (EditText) view.findViewById(R.id.editText_OTP);
                otp.setText(Utils.generateOTP());
                EditText extra_msg = (EditText) view.findViewById(R.id.editText_ExtraMSG);
                extra_msg.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE);
                extra_msg.setText("Thank you !");



                //Toasty.info(getApplicationContext(),"HENLO" + name.getText(), Toast.LENGTH_SHORT,false).show();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          if(getSupportActionBar() !=null){
              //getSupportActionBar().hide();
          }

    }

    @Override
    public boolean onSupportNavigateUp(){
        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(getSupportActionBar() !=null){
            getSupportActionBar().hide();
        }
        finish();
    }


    void send_request(final ContactModel model, final String text_msg, final String otp_code){



        StringRequest request = new StringRequest(Request.Method.POST, "https://rest.nexmo.com/sms/json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                         //stop progress dialog
                         progress_dialog.dismiss();

                        if (response == null) {
                            Toasty.info(getApplicationContext(), "Connection to the server was Unsuccesfull \n Please try again.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {

                            JSONObject response_obj = new JSONObject(response);
                            Toasty.info(getApplicationContext(),"Message queued Successfully", Toast.LENGTH_LONG,false).show();
                            Log.e("MESSAGE_INFOOOOOOOOOO_NNEEXXOOMMOO", response_obj.toString());

                            SentMessageModel sent_model = new SentMessageModel(model, System.currentTimeMillis()+"", text_msg, response, otp_code);

                            DBhandler dbh = new DBhandler(getApplicationContext());
                            dbh.addData(sent_model);


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // stop progress dialog
                progress_dialog.dismiss();


                // error in getting json
                Log.d("CONTACT_INFO_ACTIVITY", "Error: " + error);
                if(error instanceof NoConnectionError){
                    Toasty.info(getApplicationContext(), "No Internet Connection, Please try again.", Toast.LENGTH_SHORT).show();
                }else {
                    Toasty.info(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("api_key", "25520503");
                params.put("api_secret", "MWsq9aD77JZFa9Dq" );
                params.put("to", "91"+ model.getNumber());
                params.put("from", "SanskarSharma" );
                params.put("text", text_msg);



                return  params;
            }
        };

        RequestQueueSingleton.getInstance(ContactInfoActivity.this).addToRequestQueue(request);






    }





}
