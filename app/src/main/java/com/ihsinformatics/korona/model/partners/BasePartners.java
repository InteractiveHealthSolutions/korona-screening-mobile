package com.ihsinformatics.korona.model.partners;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasePartners {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("isVoided")
    @Expose
    private Boolean isVoided;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("reasonVoided")
    @Expose
    private Object reasonVoided;
    @SerializedName("partnerId")
    @Expose
    private Integer partnerId;
    @SerializedName("partnerName")
    @Expose
    private String partnerName;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("logoUrl")
    @Expose
    private String logoUrl;

    @SerializedName("website")
    @Expose
    private String url;

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

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Object getReasonVoided() {
        return reasonVoided;
    }

    public void setReasonVoided(Object reasonVoided) {
        this.reasonVoided = reasonVoided;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
