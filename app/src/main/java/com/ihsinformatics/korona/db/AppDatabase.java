package com.ihsinformatics.korona.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ihsinformatics.korona.db.dao.FormsDao;
import com.ihsinformatics.korona.db.dao.LocationDao;
import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.model.Forms;
import com.ihsinformatics.korona.model.results.AttributeResult;

@Database(entities = {Forms.class, Location.class, AttributeResult.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FormsDao getFormsDao();

    public abstract LocationDao getLocationDao();
}
