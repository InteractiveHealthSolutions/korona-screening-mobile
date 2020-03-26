package com.ihsinformatics.korona.db.entities;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ihsinformatics.korona.db.Converters;
import com.ihsinformatics.korona.model.results.AttributeResult;

import java.util.HashMap;
import java.util.List;

@Entity(tableName = "location")
public class Location {


    public Location(Integer locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }


    @PrimaryKey
    @SerializedName("locationId")
    @Expose
    private Integer locationId;

    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("categoryUuid")
    @Expose
    private String categoryUuid;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCategoryUuid() {
        return categoryUuid;
    }

    public void setCategoryUuid(String categoryUuid) {
        this.categoryUuid = categoryUuid;
    }

    @Override
    public String toString() {
        return locationName;
    }
}
