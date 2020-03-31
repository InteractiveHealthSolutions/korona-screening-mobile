package com.ihsinformatics.korona.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.activities.LoginActivity;
import com.ihsinformatics.korona.databinding.LayoutConsentBinding;
import com.ihsinformatics.korona.db.entities.Location;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsentFragment extends Fragment implements View.OnClickListener, DialogInterface.OnClickListener {

    public ConsentFragment() {
        // Required empty public constructor
    }

    LayoutConsentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.layout_consent, container, false);

        // Inflate the layout for this fragment
        init();


        return binding.getRoot();

    }

    private void init() {
        binding.layoutConsentDetail.setVisibility(View.VISIBLE);
        binding.loading.layoutLoading.setVisibility(View.GONE);
        binding.loginButton.setOnClickListener(this);
        binding.manualLocation.setOnClickListener(this);
        binding.termsLabel.setOnClickListener(this);
    }

    private boolean isValidDetails() {
        boolean isAllValid = true;

        if (!binding.agreed.isChecked()) {
            isAllValid = false;
            showToast(getString(R.string.error_term_condition));
        }

        return isAllValid;
    }

    private void showToast(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view.equals(binding.loginButton)) {
            if (isValidDetails()) {
                binding.loading.layoutLoading.setVisibility(View.VISIBLE);
                ((LoginActivity) getActivity()).fetchForm();
            }
        } else if (view.equals(binding.manualLocation)) {
            ((LoginActivity) getActivity()).changePage(1);
            removeFormError();
            showConsentLayout();
        }
        else if (view.equals(binding.termsLabel)) {
           showDialog();
        }
    }

    private void showDialog() {

        AlertDialog alertDialog = new MaterialAlertDialogBuilder(getActivity())
                .setTitle("Terms & Conditions")
                .setMessage(getString(R.string.login_info))
                .setCancelable(true)
                .setIcon(R.drawable.ic_info)
                .setNeutralButton(getString(R.string.ok), this)
                .create();

        alertDialog.show();
    }


    public void removeLoader() {
        binding.loading.layoutLoading.setVisibility(View.GONE);
    }

    public void showNoFormLayout() {
        binding.layoutNoLocation.setVisibility(View.VISIBLE);
        removeConsentLayout();
    }

    public void removeFormError() {
        binding.layoutNoLocation.setVisibility(View.GONE);
    }

    public void showConsentLayout() {
        binding.layoutConsentDetail.setVisibility(View.VISIBLE);
    }

    public void removeConsentLayout() {
        binding.layoutConsentDetail.setVisibility(View.GONE);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        //Do nothing
    }
}
