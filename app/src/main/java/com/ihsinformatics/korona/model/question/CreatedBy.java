
package com.ihsinformatics.korona.model.question;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedBy {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("isVoided")
    @Expose
    private Boolean isVoided;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("dateUpdated")
    @Expose
    private Object dateUpdated;
    @SerializedName("voidedBy")
    @Expose
    private Object voidedBy;
    @SerializedName("dateVoided")
    @Expose
    private Object dateVoided;
    @SerializedName("reasonVoided")
    @Expose
    private Object reasonVoided;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = null;
    @SerializedName("userRoles")
    @Expose
    private List<UserRole> userRoles = null;
    @SerializedName("participant")
    @Expose
    private Object participant;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getIsVoided() {
        return isVoided;
    }

    public void setIsVoided(Boolean isVoided) {
        this.isVoided = isVoided;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Object getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Object dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Object getVoidedBy() {
        return voidedBy;
    }

    public void setVoidedBy(Object voidedBy) {
        this.voidedBy = voidedBy;
    }

    public Object getDateVoided() {
        return dateVoided;
    }

    public void setDateVoided(Object dateVoided) {
        this.dateVoided = dateVoided;
    }

    public Object getReasonVoided() {
        return reasonVoided;
    }

    public void setReasonVoided(Object reasonVoided) {
        this.reasonVoided = reasonVoided;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Object getParticipant() {
        return participant;
    }

    public void setParticipant(Object participant) {
        this.participant = participant;
    }

}
