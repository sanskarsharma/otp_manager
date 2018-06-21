package com.dev.sanskar.otpmanager.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.sanskar.otpmanager.R;
import com.dev.sanskar.otpmanager.adapters.ContactsAdapter;
import com.dev.sanskar.otpmanager.adapters.SentMessagesAdapter;
import com.dev.sanskar.otpmanager.database.DBhandler;
import com.dev.sanskar.otpmanager.models.ContactModel;
import com.dev.sanskar.otpmanager.models.SentMessageModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SentMessagesFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    RecyclerView recyclerView;
    TextView tv_empty ;

    public SentMessagesFragment() {
        // Required empty public constructor
    }

    public static SentMessagesFragment newInstance(String sectionNumber) {
        SentMessagesFragment fragment = new SentMessagesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sent_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_sentmessages);
        tv_empty = view.findViewById(R.id.tv_empty);
        tv_empty.setVisibility(View.INVISIBLE);

        refreshFeed();


    }

    public void refreshFeed(){

        List<SentMessageModel> items = new ArrayList<SentMessageModel>();

        DBhandler dbh = new DBhandler(getActivity());
        items = dbh.getAllEntries();

        if(!items.isEmpty()){
            if(tv_empty.getVisibility() == View.VISIBLE){
                tv_empty.setVisibility(View.INVISIBLE);
            }


            Log.e("fromfeed_sentmessages()"," items is NOT empty");
            Log.e("fromfeed_sentmessages()"," items : \n "+ items.toString());

            Collections.reverse(items);             // reversing list so that new entries show on top

            SentMessagesAdapter adapter = new SentMessagesAdapter(items, getActivity());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }else {

            tv_empty.setVisibility(View.VISIBLE);
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        refreshFeed();
    }

    
}
