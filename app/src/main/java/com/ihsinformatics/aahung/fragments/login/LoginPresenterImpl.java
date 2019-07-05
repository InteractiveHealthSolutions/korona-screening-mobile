package com.ihsinformatics.aahung.fragments.login;

import android.content.Context;

import com.ihsinformatics.aahung.network.ApiService;

public class LoginPresenterImpl implements LoginContract.Presenter {


    private Context context;
    private ApiService apiService;
    private LoginContract.View view;

    public LoginPresenterImpl(Context context, ApiService apiService) {
        this.context = context;
        this.apiService = apiService;
    }

    @Override
    public void login(String username, String user) {
        view.startMainActivity();

    }

    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }
}
