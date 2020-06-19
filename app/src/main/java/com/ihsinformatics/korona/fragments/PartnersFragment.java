package com.ihsinformatics.korona.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.activities.LoginActivity;
import com.ihsinformatics.korona.activities.ResultActivity;
import com.ihsinformatics.korona.activities.TabbedActivity;
import com.ihsinformatics.korona.adapter.PartnersAdapter;
import com.ihsinformatics.korona.databinding.FragmentPartnersBinding;
import com.ihsinformatics.korona.model.partners.BasePartners;
import com.ihsinformatics.korona.network.ResponseListener;
import com.ihsinformatics.korona.network.RestServices;

import java.util.List;

import javax.inject.Inject;


public class PartnersFragment extends Fragment implements View.OnClickListener {

    @Inject
    RestServices restServices;

    FragmentPartnersBinding binding;

    public PartnersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_partners, container, false);

        init();
        return binding.getRoot();
    }

    private void init() {
        getPartners();
        binding.backButton.setOnClickListener(this);
        binding.loading.refresh.setOnClickListener(this);


    }

    private void getPartners() {
        restServices.getPartners(new ResponseListener.PartnerListener() {
            @Override
            public void onSuccess(List<BasePartners> partners) {
                binding.loading.layoutLoading.setVisibility(View.GONE);
                binding.recycler.setVisibility(View.VISIBLE);
                PartnersAdapter adapter = new PartnersAdapter(partners);
                binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                binding.recycler.setHasFixedSize(true);
                binding.recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(String message) {
                toggleRefresh(View.VISIBLE);
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.equals(binding.backButton)) {
            if (getActivity() instanceof LoginActivity) {
                ((LoginActivity) getActivity()).hidePartners();
            } else if (getActivity() instanceof ResultActivity) {
                ((ResultActivity) getActivity()).hidePartners();
            }else if (getActivity() instanceof TabbedActivity) {
                ((TabbedActivity) getActivity()).onSupportNavigateUp();
            }
        } else if (view.equals(binding.loading.refresh)) {
            {
                toggleRefresh(View.GONE);
                getPartners();
            }
        }
    }


    public void toggleRefresh(int visibility) {

        binding.loading.layoutLoading.setVisibility(View.VISIBLE);
        binding.recycler.setVisibility(View.GONE);
        binding.loading.refresh.setVisibility(visibility);
        binding.loading.loadingText.setVisibility(visibility == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
        binding.loading.loadingView.setVisibility(visibility == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }
}
