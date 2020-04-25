package com.ihsinformatics.korona.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.adapter.PartnersAdapter;
import com.ihsinformatics.korona.model.partners.BasePartners;

import java.util.ArrayList;
import java.util.List;


public class PartnersFragment extends Fragment {

    public PartnersFragment() {
        // Required empty public constructor
    }


    public static PartnersFragment newInstance(String param1, String param2) {
        PartnersFragment fragment = new PartnersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partners, container, false);
        init();
        return view;
    }

    private void init() {
        List<BasePartners> partners = new ArrayList<>();

        PartnersAdapter adapter = new PartnersAdapter(partners);
    }
}
