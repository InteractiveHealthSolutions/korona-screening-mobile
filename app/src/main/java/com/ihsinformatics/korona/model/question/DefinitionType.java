
package com.ihsinformatics.korona.model.question;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefinitionType {

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
    @SerializedName("definitionTypeId")
    @Expose
    private Integer definitionTypeId;
    @SerializedName("typeName")
    @Expose
    private String typeName;
    @SerializedName("shortName")
    @Expose
    private String shortName;

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

    public Integer getDefinitionTypeId() {
        return definitionTypeId;
    }

    public void setDefinitionTypeId(Integer definitionTypeId) {
        this.definitionTypeId = definitionTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
