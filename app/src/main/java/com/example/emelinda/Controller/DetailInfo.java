package com.example.emelinda.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.emelinda.R;


public class DetailInfo extends Fragment {
    TextView detail_name;
    TextView detail_address;
    TextView detail_phone;
    TextView detail_website;
    TextView detail_type;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailInfo() {
    }


    public static DetailInfo newInstance(String param1, String param2) {
        DetailInfo fragment = new DetailInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_info_a, container, false);
        detail_name     = (TextView) v.findViewById(R.id.detail_name);
        detail_address  = (TextView) v.findViewById(R.id.detail_address);
        detail_phone    = (TextView) v.findViewById(R.id.detail_phone);
        detail_website  = (TextView) v.findViewById(R.id.detail_website);
        detail_type     = (TextView) v.findViewById(R.id.detail_type);

        detail_name.setText     (List.library.getName());
        detail_address.setText  (List.library.getAddress());
        detail_phone.setText    (List.library.getPhone());
        detail_website.setText  (List.library.getWebsite());
        detail_type.setText     (List.library.getType().getDescription());
            return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
