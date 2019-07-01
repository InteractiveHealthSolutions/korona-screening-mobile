package com.ihsinformatics.aahung.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.ihsinformatics.aahung.App;
import com.ihsinformatics.aahung.R;
import com.ihsinformatics.aahung.fragments.login.LoginContract;
import com.ihsinformatics.aahung.network.ApiService;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements LoginContract.View {

    @Inject
    SharedPreferences preferences;

    @Inject
    ApiService apiService;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App) getApplication()).getComponent().inject(this);
        presenter.takeView(this);
        presenter.login("abdulmoizahmed");
    }



    @Override
    public void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
