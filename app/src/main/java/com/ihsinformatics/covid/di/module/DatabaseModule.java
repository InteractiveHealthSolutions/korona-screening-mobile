package com.ihsinformatics.covid.di.module;


import android.app.Application;

import androidx.room.Room;

import com.ihsinformatics.covid.db.AppDatabase;
import com.ihsinformatics.covid.db.dao.FormsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private AppDatabase appDatabase;


    public DatabaseModule(Application application) {
        appDatabase = Room.databaseBuilder(application, AppDatabase.class, "covid-db").allowMainThreadQueries().build();
    }

    @Singleton
    @Provides
    public AppDatabase provideAppDatabase() {
        return appDatabase;
    }

    @Singleton
    @Provides
    public FormsDao provideFormDao() {
        return appDatabase.getFormsDao();
    }
}
