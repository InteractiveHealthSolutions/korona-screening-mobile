package com.ihsinformatics.korona.model.results;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.ihsinformatics.korona.db.Converters;

import java.io.Serializable;

@Entity(tableName = "attributes", primaryKeys = {"contextId","attributeId"})
public class AttributeResult implements Serializable {



    @NonNull // its location ID
    private Integer contextId;
    @SerializedName("attributeId")
    @Expose
    @NonNull
    private Integer attributeId;
    @SerializedName("attributeType")
    @Expose
    @TypeConverters(Converters.class)
    private AttributeType attributeType;

    @SerializedName("attributeValue")
    @Expose
    private String attributeValue;

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public AttributeType getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    public Integer getContextId() {
        return contextId;
    }

    public void setContextId(Integer contextId) {
        this.contextId = contextId;
    }
}
