package com.ihsinformatics.korona.activities;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.databinding.ActivityTabbedBinding;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.util.List;

import static com.ihsinformatics.korona.activities.LoginActivity.FORM_TYPE;

public class TabbedActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    ActivityTabbedBinding binding;
    List<QuizResponse> formTypeResponses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tabbed);
        init();
    }

    private void init() {

        String formType = getIntent().getStringExtra(FORM_TYPE);

        Bundle bundle = new Bundle();
        bundle.putString(FORM_TYPE, formType);
        NavHostFragment navHostFragment = NavHostFragment.create(R.navigation.home_navigation, bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, navHostFragment)
                .setPrimaryNavigationFragment(navHostFragment) // equivalent to app:defaultNavHost="true"
                .commit();


    }


    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }


    @Override
    public void onBackPressed() {

        String message = null;

        if (!Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getLabel().equals("FormFragment")) {
            message = "Are you sure you want to exit?";
        } else {
            message = "Are you sure you want to go to the main screen?";
        }

        AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle("")
                .setMessage(message)
                .setCancelable(true)
                .setIcon(R.drawable.ic_info)
                .setPositiveButton("Yes", this)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create();

        alertDialog.show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        super.onBackPressed();
    }

}
