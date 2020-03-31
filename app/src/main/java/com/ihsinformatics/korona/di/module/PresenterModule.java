package com.ihsinformatics.korona.di.module;

import android.content.Context;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.ihsinformatics.korona.common.DevicePreferences;
import com.ihsinformatics.korona.db.AppDatabase;
import com.ihsinformatics.korona.fragments.form.FormContract;
import com.ihsinformatics.korona.fragments.form.FormPresenterImpl;
import com.ihsinformatics.korona.fragments.location.automatic.DetectLocationContract;
import com.ihsinformatics.korona.fragments.location.automatic.DetectLocationPresenterImpl;
import com.ihsinformatics.korona.fragments.location.manual.ManualLocationContract;
import com.ihsinformatics.korona.fragments.location.manual.ManualLocationPresenterImpl;
import com.ihsinformatics.korona.fragments.login.LoginContract;
import com.ihsinformatics.korona.fragments.login.LoginPresenterImpl;
import com.ihsinformatics.korona.fragments.result.ResultContract;
import com.ihsinformatics.korona.fragments.result.ResultPresenterImpl;
import com.ihsinformatics.korona.network.ApiService;
import com.ihsinformatics.korona.network.RestServices;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    public LoginContract.Presenter providesLoginPresenter(final RestServices restService, final DevicePreferences preferences, final AppDatabase appdatabase) {
        return new LoginPresenterImpl(restService,preferences,appdatabase);
    }

    @Provides
    public DetectLocationContract.Presenter providesDetectLocationPresenter(final RestServices restService, final DevicePreferences preferences, final AppDatabase appdatabase) {
        return new DetectLocationPresenterImpl(restService,preferences,appdatabase);
    }

    @Provides
    public ManualLocationContract.Presenter providesManualLocationPresenter(final RestServices restService, final DevicePreferences preferences, final AppDatabase appdatabase) {
        return new ManualLocationPresenterImpl(restService,preferences,appdatabase);
    }


    @Provides
    public FormContract.Presenter providesFormPresenter( final RestServices restServices) {
        return new FormPresenterImpl(restServices);
    }

    @Provides
    public ResultContract.Presenter providesResultPresenter( ) {
        return new ResultPresenterImpl();
    }


}
