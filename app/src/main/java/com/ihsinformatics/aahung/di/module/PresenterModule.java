package com.ihsinformatics.aahung.di.module;

import com.ihsinformatics.aahung.fragments.login.LoginContract;
import com.ihsinformatics.aahung.fragments.login.LoginPresenterImpl;
import com.ihsinformatics.aahung.network.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    public LoginContract.Presenter providesLoginPresenter(final ApiService apiService) {
        return new LoginPresenterImpl(apiService);
    }
}