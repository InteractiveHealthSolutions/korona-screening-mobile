package com.ihsinformatics.covid.di.module;

import android.content.Context;

import com.ihsinformatics.covid.common.DevicePreferences;
import com.ihsinformatics.covid.fragments.form.FormContract;
import com.ihsinformatics.covid.fragments.form.FormPresenterImpl;
import com.ihsinformatics.covid.fragments.login.LoginContract;
import com.ihsinformatics.covid.fragments.login.LoginPresenterImpl;
import com.ihsinformatics.covid.network.ApiService;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    public LoginContract.Presenter providesLoginPresenter(final Context context, final ApiService apiService, final DevicePreferences preferences) {
        return new LoginPresenterImpl(context, apiService,preferences);
    }

    @Provides
    public FormContract.Presenter providesFormPresenter( final ApiService apiService) {
        return new FormPresenterImpl(apiService);
    }
}
