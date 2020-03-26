package com.ihsinformatics.korona.di.module;


import android.app.Application;

import androidx.room.Room;

import com.ihsinformatics.korona.db.AppDatabase;
import com.ihsinformatics.korona.db.dao.FormsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    private static final String DB_NAME = "covid-19-db";

    @Singleton
    @Provides
    public AppDatabase provideAppDatabase(Application application) {
        return  Room.databaseBuilder(application, AppDatabase.class, DB_NAME).allowMainThreadQueries()/*.addMigrations(MIGRATION_1_2,MIGRATION_2_3)*/.build();
    }

    @Singleton
    @Provides
    public FormsDao provideFormDao(AppDatabase appDatabase) {
        return appDatabase.getFormsDao();
    }
}
