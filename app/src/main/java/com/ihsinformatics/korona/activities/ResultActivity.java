package com.ihsinformatics.korona.activities;

import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.databinding.ActivityResultBinding;
import com.ihsinformatics.korona.databinding.ItemCallBinding;
import com.ihsinformatics.korona.databinding.ItemHospitalBinding;
import com.ihsinformatics.korona.fragments.result.ResultContract;
import com.ihsinformatics.korona.model.Location;

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

        binding.score.setTextColor(getResources().getColor(resId));
        binding.status.setTextColor(getResources().getColor(resId));
    }

    @Override
    public void showLocations(List<Location> locations) {

        int margin = getResources().getDimensionPixelOffset(R.dimen.margin_small);

        for (Location location : locations) {
            ItemHospitalBinding itemBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_hospital, null, false);
            itemBinding.hospitalName.setText(location.getName());
            itemBinding.call.setTag(location.getContactNo());
            itemBinding.location.setTag(location.getAddress());
            itemBinding.call.setOnClickListener(new CallListener());
            itemBinding.location.setOnClickListener(new AddressListener());
            CardView root = (CardView)  itemBinding.getRoot();
            LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardViewParams.setMargins(0, margin, 0, margin);
            root.setLayoutParams(cardViewParams);
            root.requestLayout();
            binding.layoutDetails.addView(itemBinding.getRoot());
        }
    }

    @Override
    public void showContact(List<String> contacts) {

        int margin = getResources().getDimensionPixelOffset(R.dimen.margin_small);

        for (String contact : contacts) {
            ItemCallBinding itemBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.item_call, null, false);
            itemBinding.number.setText(contact);
            itemBinding.getRoot().setTag(contact);
            itemBinding.getRoot().setOnClickListener(new CallListener());

            CardView root = (CardView)  itemBinding.getRoot();
            LinearLayout.LayoutParams cardViewParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cardViewParams.setMargins(margin, 0, margin, 0);
            root.setLayoutParams(cardViewParams);
            root.requestLayout();

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
            try {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phNumber));
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            startActivity(new Intent(ResultActivity.this, InstructionActivity.class));
        }
    }
}
