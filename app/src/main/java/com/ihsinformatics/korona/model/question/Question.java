
package com.ihsinformatics.korona.model.question;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("isRetired")
    @Expose
    private Boolean isRetired;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("reasonRetired")
    @Expose
    private Object reasonRetired;
    @SerializedName("elementId")
    @Expose
    private Integer elementId;
    @SerializedName("elementName")
    @Expose
    private String elementName;
    @SerializedName("dataType")
    @Expose
    private String dataType;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("validationRegex")
    @Expose
    private String validationRegex;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsRetired() {
        return isRetired;
    }

    public void setIsRetired(Boolean isRetired) {
        this.isRetired = isRetired;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Object getReasonRetired() {
        return reasonRetired;
    }

    public void setReasonRetired(Object reasonRetired) {
        this.reasonRetired = reasonRetired;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getValidationRegex() {
        return validationRegex;
    }

    public void setValidationRegex(String validationRegex) {
        this.validationRegex = validationRegex;
    }

}
