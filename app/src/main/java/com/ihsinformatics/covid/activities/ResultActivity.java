package com.ihsinformatics.covid.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ihsinformatics.covid.App;
import com.ihsinformatics.covid.R;
import com.ihsinformatics.covid.databinding.ActivityResultBinding;
import com.ihsinformatics.covid.databinding.ItemCallBinding;
import com.ihsinformatics.covid.databinding.ItemHospitalBinding;
import com.ihsinformatics.covid.fragments.result.ResultContract;
import com.ihsinformatics.covid.model.Location;

import java.util.List;

import javax.inject.Inject;

public class ResultActivity extends BaseActivity implements ResultContract.View {

    ActivityResultBinding binding;

    @Inject
    ResultContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        presenter.takeView(this);
        int score = getIntent().getIntExtra("score", 0);
        binding.score.setText("" + score);
        presenter.setScore(score);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setResultDetail(String details) {
        binding.resultDetails.setText(details);
    }

    @Override
    public String getStringResource(int resId) {
        return getResources().getString(resId);
    }

    @Override
    public void setColor(int resId) {
        binding.score.setTextColor(resId);
        binding.status.setTextColor(resId);
    }

    @Override
    public void showLocations(List<Location> locations) {


        for (Location location : locations) {
            ItemHospitalBinding itemBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_hospital, null, false);
            itemBinding.hospitalName.setText(location.getName());
            itemBinding.call.setTag(location.getContactNo());
            itemBinding.location.setTag(location.getAddress());
            itemBinding.call.setOnClickListener(new CallListener());
            itemBinding.location.setOnClickListener(new AddressListener());
            binding.layoutDetails.addView(itemBinding.getRoot());
        }

    }

    @Override
    public void showContact(List<String> contacts) {

        for (String contact : contacts) {
            ItemCallBinding itemBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_call, null, false);
            itemBinding.number.setText(contact);
            itemBinding.getRoot().setTag(contact);
            itemBinding.getRoot().setOnClickListener(new CallListener());
            binding.layoutContacts.addView(itemBinding.getRoot());
        }
    }

    @Override
    public void setStatus(int resId) {
        binding.status.setText(getString(resId));
    }

    @Override
    public void showPrecautions() {
        binding.precaution.setVisibility(View.VISIBLE);
    }

    @Override
    public void showHighRiskDetails() {
        binding.layoutContactRoot.setVisibility(View.VISIBLE);
        binding.instruction.setVisibility(View.VISIBLE);
        binding.instruction.setOnClickListener(new InstructionListener());
        binding.layoutDetails.setVisibility(View.VISIBLE);
    }


    private class CallListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String phNumber = view.getTag().toString();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phNumber));
            startActivity(intent);
        }
    }

    private class AddressListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String coordinates = view.getTag().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + coordinates + "?q=" + coordinates + "(Hospital)"));
            startActivity(intent);
        }
    }

    private class InstructionListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(ResultActivity.this,InstructionActivity.class));
        }
    }
}
