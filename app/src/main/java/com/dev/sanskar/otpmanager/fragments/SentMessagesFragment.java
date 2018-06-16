package com.dev.sanskar.otpmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.sanskar.otpmanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SentMessagesFragment extends Fragment {


    public SentMessagesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sent_messages, container, false);
    }

}
