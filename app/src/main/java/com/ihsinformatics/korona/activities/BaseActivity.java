package com.ihsinformatics.korona.activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import androidx.appcompat.app.AppCompatActivity;

import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.common.DevicePreferences;
import com.ihsinformatics.korona.model.Language;


import java.util.Locale;

import javax.inject.Inject;

public class BaseActivity extends AppCompatActivity {

    @Inject
    DevicePreferences devicePreferences;

    @Override
    protected void attachBaseContext(Context base) {
        ((App) base.getApplicationContext()).getComponent().inject(this);
        super.attachBaseContext(updateBaseContextLocale(base));
    }

    private Context updateBaseContextLocale(Context base) {
        Language selectedLanguage = devicePreferences.getLanguage();
        if (selectedLanguage != null) {
           base =  updateLocale(base, selectedLanguage.getLocale());
        }

        return base;
    }

    public Context updateLocale(Context context, String language) {

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResourcesLocale(context, locale);
        }

        return updateResourcesLocaleLegacy(context, locale);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private Context updateResourcesLocale(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        LocaleList localeList = new LocaleList(locale);
        configuration.setLocales(localeList);

        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }



}
