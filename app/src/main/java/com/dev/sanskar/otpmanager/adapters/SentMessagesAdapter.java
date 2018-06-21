package com.dev.sanskar.otpmanager.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.dev.sanskar.otpmanager.R;
import com.dev.sanskar.otpmanager.models.ContactModel;
import com.dev.sanskar.otpmanager.models.SentMessageModel;
import com.dev.sanskar.otpmanager.utils.Utils;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by hadoop on 16/6/18.
 */

public class SentMessagesAdapter extends RecyclerView.Adapter<SentMessagesAdapter.MyViewHolder>{

    private List<SentMessageModel> dataList;
    private Context context;

    private int positionoooo;
    public int getPositionoooo() {
        return positionoooo;
    }
    public void setPositionoooo(int positionoooo) {
        this.positionoooo = positionoooo;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sent_messages, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final SentMessageModel datu = dataList.get(position);

        String[] arr = Utils.getDateandtime(Long.parseLong(datu.getSent_timestamp())).split("-");
        String entryDate= arr[0];
        String entryTime= arr[1];

        holder.datamodel = datu;

        holder.otp.setText("OTP : "+datu.getOtp_code());

        ContactModel contact = datu.getContact();
        holder.contact_info.setText(contact.getName()+ " ("+contact.getNumber()+")");
        holder.day_date.setText(entryDate);
        holder.time.setText(entryTime);

        holder.parentCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SentMessageModel model = datu;

            Toasty.info(view.getContext(), datu.
                getOtp_code(), Toast.LENGTH_SHORT, false).show();

                MaterialDialog dialog = new MaterialDialog.Builder(context)
                        .title(datu.getContact().getName())
                        .content("OTP : "+datu.getOtp_code()
                            + "\n" + "Full Message :\n " + datu.getSent_message()
                        )
                        .negativeText("Dismiss").show();



            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



    public SentMessagesAdapter() {
    }

    public SentMessagesAdapter(List<SentMessageModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView contact_info, otp, day_date, time;
        private CardView parentCardview;
        private SentMessageModel datamodel;


        public MyViewHolder(View view) {
            super(view);

            parentCardview = (CardView) view.findViewById(R.id.parent_cardview_sentmessages);
            datamodel = null;

            contact_info = (TextView) view.findViewById(R.id.row_1_tv);

            otp = (TextView) view.findViewById(R.id.row_3_tv);
            day_date = (TextView) view.findViewById(R.id.day_date_tv);
            time = (TextView) view.findViewById(R.id.time_tv);


        }
    }

}
