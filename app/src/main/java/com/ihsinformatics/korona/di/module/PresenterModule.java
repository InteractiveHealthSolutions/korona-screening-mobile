package com.ihsinformatics.korona.di.module;

import android.content.Context;

import com.ihsinformatics.korona.common.DevicePreferences;
import com.ihsinformatics.korona.fragments.form.FormContract;
import com.ihsinformatics.korona.fragments.form.FormPresenterImpl;
import com.ihsinformatics.korona.fragments.login.LoginContract;
import com.ihsinformatics.korona.fragments.login.LoginPresenterImpl;
import com.ihsinformatics.korona.fragments.result.ResultContract;
import com.ihsinformatics.korona.fragments.result.ResultPresenterImpl;
import com.ihsinformatics.korona.network.ApiService;

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

    @Provides
    public ResultContract.Presenter providesResultPresenter( ) {
        return new ResultPresenterImpl();
    }
}
