package com.ihsinformatics.aahung.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "forms")
public class Forms {

    @PrimaryKey(autoGenerate = true)
    public int formId;

    public String formType;

    public String formData;

    public boolean isSubmitted;
}
