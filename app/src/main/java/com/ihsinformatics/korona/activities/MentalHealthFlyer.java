package com.ihsinformatics.korona.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ihsinformatics.korona.R;

import static com.ihsinformatics.korona.activities.ResultActivity.SHOULD_GO_BACK;

public class MentalHealthFlyer extends AppCompatActivity {

    boolean canGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mental_health_flyer);
        canGoBack = getIntent().getBooleanExtra(SHOULD_GO_BACK, false);
        if (!canGoBack)
            findViewById(R.id.back_to_result).setVisibility(View.GONE);

    }

    public void onHomeButtonClicked(View view) {
        startHomeScreen();
    }

    private void startHomeScreen() {
        Intent intent = new Intent(MentalHealthFlyer.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void onCallButtonClicked(View view) {
        String phNumber = "0213 713 3332";
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phNumber));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackButtonClicked(View view) {

        if (!canGoBack) {
            showBackDialog();

        } else {
            MentalHealthFlyer.this.finish();
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
                        startHomeScreen();
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


    @Override
    public void onBackPressed() {

        if (!canGoBack) {
            showBackDialog();
        } else
            MentalHealthFlyer.this.finish();
    }
}
