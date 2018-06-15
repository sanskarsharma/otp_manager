package com.dev.sanskar.otpmanager.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dev.sanskar.otpmanager.R;
import com.dev.sanskar.otpmanager.models.ContactModel;
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

public class ContactInfoActivity extends DraggerActivity {

    TextView name, number;
    FloatingActionButton fab_sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        setDraggerPosition(DraggerPosition.TOP);
        setSlideEnabled(false);

        setTitle("Contact Details");
        name = (TextView) findViewById(R.id.tv_info_name);
        number = (TextView) findViewById(R.id.tv_info_number);
        fab_sms = (FloatingActionButton) findViewById(R.id.fab_sms) ;


        Intent intent = getIntent();
        final ContactModel model = (ContactModel) intent.getParcelableExtra("model");
        name.setText(model.getName());
        number.setText(model.getNumber());

        fab_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_request(model);
                //Toasty.info(getApplicationContext(),"HENLO" + name.getText(), Toast.LENGTH_SHORT,false).show();
            }
        });

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
          if(getSupportActionBar() !=null){
              getSupportActionBar().hide();
          }

    }

//    @Override
//    public boolean onSupportNavigateUp(){
//        closeActivity();
//        return true;
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeActivity();
    }


    void send_request(final ContactModel model){



        StringRequest request = new StringRequest(Request.Method.POST, "https://rest.nexmo.com/sms/json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // stop progress dialog
                        // progressDialog.dismiss();

                        if (response == null) {
                            Toasty.info(getApplicationContext(), "Profile registration to the Server was Unsuccesfull \n Please try again via 'Sync with server' in 3-dot menu ", Toast.LENGTH_LONG).show();
                            return;
                        }

                        try {

                            JSONObject response_obj = new JSONObject(response);
                            Toasty.info(getApplicationContext(),"RESPONSE : " + response_obj.getString("message-count"), Toast.LENGTH_LONG,false).show();
                            Log.e("MESSAGE_INFOOOOOOOOOO_NNEEXXOOMMOO", response_obj.toString());
//                            String status = response_obj.getString("status");

//                            if(status.equals("OK")){

//                                String mobile_number = response_obj.getString("mobile_number");
//                                Toasty.info(getApplicationContext(), "Authentication Successful !", Toast.LENGTH_SHORT).show();
//
//
//                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                        num_again,        // Phone number to verify
//                                        60,                 // Timeout duration
//                                        TimeUnit.SECONDS,   // Unit of timeout
//                                        LoginActivity.this,               // Activity (for callback binding)
//                                        mCallbacks);        // OnVerificationStateChangedCallbacks



//                            }else if(status.equals("FAIL")){

//                                String status_message = "";
//                                if(response_obj.has("message") && !response_obj.isNull("message")){
//                                    status_message = response_obj.getString("message");
//                                }
//
//                                MaterialDialog materialDialog = new MaterialDialog.Builder(LoginActivity.this)
//                                        .title("Authentication Failed")
//                                        .titleColor(Color.parseColor("#ff8533"))
//                                        .content("You contact number is not authenticated to use this app. \nAuth status : "+status_message+"\nIf you think this is a mistake, please contact admin. \nEmail : feed@ssipmt.com")
//                                        .contentColor(Color.BLACK)
//                                        .icon(getDrawable(R.drawable.ic_info_black_24dp))
//                                        .positiveText("Dismiss")
//                                        .show();
//                            }


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.d("CONTACT_INFO_ACTIVITY", "Error: " + error.getMessage());

                // stop progress dialog
                //progressDialog.dismiss();

                Toasty.info(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                params.put("api_key", "25520503");
                params.put("api_secret", "MWsq9aD77JZFa9Dq" );
                params.put("to", "91"+ model.getNumber());
                params.put("from", "SanskarSharma" );
                params.put("text", "This_is_just_texting");



                return  params;
            }
        };

        RequestQueueSingleton.getInstance(ContactInfoActivity.this).addToRequestQueue(request);






    }



}
