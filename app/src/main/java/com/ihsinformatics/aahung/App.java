package com.ihsinformatics.aahung;

import android.app.Application;

import com.ihsinformatics.aahung.di.component.AppComponent;
import com.ihsinformatics.aahung.di.component.DaggerAppComponent;
import com.ihsinformatics.aahung.di.module.ApplicationModule;

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
