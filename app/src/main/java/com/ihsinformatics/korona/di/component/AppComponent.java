package com.ihsinformatics.korona.di.component;

import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.activities.BaseActivity;
import com.ihsinformatics.korona.activities.LoginActivity;
import com.ihsinformatics.korona.activities.MainActivity;
import com.ihsinformatics.korona.activities.ResultActivity;
import com.ihsinformatics.korona.activities.SplashActivity;
import com.ihsinformatics.korona.di.module.ApplicationModule;
import com.ihsinformatics.korona.di.module.DatabaseModule;
import com.ihsinformatics.korona.di.module.FormModule;
import com.ihsinformatics.korona.di.module.NetworkModule;
import com.ihsinformatics.korona.di.module.PresenterModule;
import com.ihsinformatics.korona.fragments.PartnersFragment;
import com.ihsinformatics.korona.fragments.form.FormFragment;
import com.ihsinformatics.korona.fragments.location.automatic.DetectLocationFragment;
import com.ihsinformatics.korona.fragments.location.manual.ManualLocationFragment;

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

    void inject(SplashActivity splashActivity);

    void inject(ManualLocationFragment manualLocationFragment);

    void inject(DetectLocationFragment detectLocationFragment);

    void inject(PartnersFragment partnersFragment);

    void inject(FormFragment formFragment);
}
