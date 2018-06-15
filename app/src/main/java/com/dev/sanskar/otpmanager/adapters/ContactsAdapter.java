package com.dev.sanskar.otpmanager.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.sanskar.otpmanager.R;
import com.dev.sanskar.otpmanager.activities.ContactInfoActivity;
import com.dev.sanskar.otpmanager.models.ContactModel;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by hadoop on 15/6/18.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {


    private List<ContactModel> dataList;

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
                .inflate(R.layout.row_contacts, parent, false);

        //itemView.setOnClickListener(ScrollingActivity.myOnClickListener);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {



        final ContactModel datu = dataList.get(position);


        holder.datamodel = datu;

        holder.name.setText(datu.getName());
        holder.number.setText(datu.getNumber());


//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                setPositionoooo(holder.getAdapterPosition());
//                Log.e("FROM LOOOOONG CLICK", "YAHA SAB THK HAI");
//                return false;
//            }
//        });

        holder.contact_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContactModel model = datu;

                Intent intu = new Intent(view.getContext(), ContactInfoActivity.class);
                intu.putExtra("model", model);
                view.getContext().startActivity(intu);

                Toasty.info(view.getContext(), datu.getName(), Toast.LENGTH_SHORT, false).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public ContactsAdapter() {
    }

    public ContactsAdapter(List<ContactModel> dataList) {
        this.dataList = dataList;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name,  number;
        private ContactModel datamodel;
        private RelativeLayout contact_container;

        public MyViewHolder(View view) {
            super(view);

            datamodel = null;
            contact_container = (RelativeLayout) view.findViewById(R.id.row_contact_container);
            name = (TextView) view.findViewById(R.id.row_1_contact);
            number = (TextView) view.findViewById(R.id.row_2_contact);

        }


    }


}