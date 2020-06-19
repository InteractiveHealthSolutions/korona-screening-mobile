package com.ihsinformatics.korona.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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

    public static final String SHOULD_GO_BACK = "shouldGoBack";
    ActivityResultBinding binding;

    @Inject
    ResultContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getComponent().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        presenter.takeView(this);
        String result = getIntent().getStringExtra("result");
        String country = getIntent().getStringExtra("location");
        if (country.equalsIgnoreCase("pakistan")) {
            binding.next.setVisibility(View.VISIBLE);
            binding.instruction.setVisibility(View.GONE);
            //showMentalHealthDialog();
        }
        setResultDetail(result);
        presenter.getPhoneNumbers(result);
        binding.instruction.setOnClickListener(new InstructionListener());
    }

/*    private void showMentalHealthDialog() {
        View flyerView = getLayoutInflater().inflate(R.layout.layout_mental_health_flyer, null);

        AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setView(flyerView)
                .setCancelable(false)
                .create();


        flyerView.findViewById(R.id.cancel_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }*/

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

        //binding.score.setTextColor(getResources().getColor(resId));
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
            CardView root = (CardView) itemBinding.getRoot();
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

            CardView root = (CardView) itemBinding.getRoot();
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

        binding.layoutDetails.setVisibility(View.VISIBLE);
    }

    public void onPartnersButtonClicked(View view) {
        binding.mainLayout.setVisibility(View.GONE);
    }


    public void hidePartners() {
        binding.mainLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onBackPressed() {
        if(binding.mainLayout.getVisibility()  == View.VISIBLE) {
           showBackDialog();
        }else {
            hidePartners();
        }
    }

    private void showBackDialog() {
        AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle("")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(true)
                .setIcon(R.drawable.ic_info)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        goHome();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();

        alertDialog.show();
    }

    public void onNextButtonClicked(View view) {

        Intent intent = new Intent(ResultActivity.this, MentalHealthFlyer.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(SHOULD_GO_BACK,true);
        startActivity(intent);

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
            //  startActivity(new Intent(ResultActivity.this, InstructionActivity.class));
            goHome();
        }
    }

    private void goHome() {
        Intent intent = new Intent(ResultActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
