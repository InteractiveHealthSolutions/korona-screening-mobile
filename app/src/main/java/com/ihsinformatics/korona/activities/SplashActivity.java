package com.ihsinformatics.korona.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.databinding.ActivitySplash2Binding;
import com.ihsinformatics.korona.db.AppDatabase;
import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.network.ResponseListener;
import com.ihsinformatics.korona.network.RestServices;

import java.util.List;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    @Inject
    RestServices restServices;

    @Inject
    AppDatabase appDatabase;

    ActivitySplash2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash2);
        ((App) getApplicationContext()).getComponent().inject(this);
        //binding.refresh.setOnClickListener(this);
        getLocation();

    }

    private void getLocation() {

    }

    private void startLoginActivity() {
        Intent intent = new Intent(this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        getLocation();
        //toggleRefresh(View.INVISIBLE);
    }


}
