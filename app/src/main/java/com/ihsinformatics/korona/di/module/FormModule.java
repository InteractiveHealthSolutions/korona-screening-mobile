package com.ihsinformatics.korona.di.module;


import android.content.Context;
import android.widget.LinearLayout;

import com.ihsinformatics.korona.views.FormBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FormModule {

    @Provides
    public LinearLayout provideLayout(Context context) {
        return new LinearLayout(context);
    }

    @Singleton
    @Provides
    public FormBuilder provideFormBuilder(Context context, LinearLayout layout) {
        return new FormBuilder(context, layout);
    }


}
