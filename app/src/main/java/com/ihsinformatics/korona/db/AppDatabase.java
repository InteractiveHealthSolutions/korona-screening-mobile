package com.ihsinformatics.korona.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ihsinformatics.korona.db.dao.FormsDao;
import com.ihsinformatics.korona.model.Forms;

@Database(entities = {Forms.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FormsDao getFormsDao();
}
