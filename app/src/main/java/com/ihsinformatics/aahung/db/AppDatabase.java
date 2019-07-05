package com.ihsinformatics.aahung.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ihsinformatics.aahung.db.dao.FormsDao;
import com.ihsinformatics.aahung.model.Forms;

@Database(entities = {Forms.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FormsDao getFormsDao();
}
