package com.ihsinformatics.covid.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ihsinformatics.covid.db.dao.FormsDao;
import com.ihsinformatics.covid.model.Forms;

@Database(entities = {Forms.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FormsDao getFormsDao();
}
