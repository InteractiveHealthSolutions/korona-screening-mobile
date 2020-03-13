package com.ihsinformatics.covid.di.component;

import com.ihsinformatics.covid.App;
import com.ihsinformatics.covid.activities.BaseActivity;
import com.ihsinformatics.covid.activities.LoginActivity;
import com.ihsinformatics.covid.activities.MainActivity;
import com.ihsinformatics.covid.activities.ResultActivity;
import com.ihsinformatics.covid.di.module.ApplicationModule;
import com.ihsinformatics.covid.di.module.DatabaseModule;
import com.ihsinformatics.covid.di.module.FormModule;
import com.ihsinformatics.covid.di.module.NetworkModule;
import com.ihsinformatics.covid.di.module.PresenterModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, PresenterModule.class, DatabaseModule.class, FormModule.class})
public interface AppComponent {
    void inject(App target);
    void inject(LoginActivity target);
    void inject(MainActivity target);
    void inject(BaseActivity baseActivity);

    void inject(ResultActivity resultActivity);
}
