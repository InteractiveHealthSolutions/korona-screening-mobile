package com.ihsinformatics.covid;

import android.app.Application;

import com.ihsinformatics.covid.di.component.AppComponent;

import com.ihsinformatics.covid.di.component.DaggerAppComponent;
import com.ihsinformatics.covid.di.module.ApplicationModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    private AppComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Light.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        component = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }

}
