package com.ihsinformatics.korona.model.geocode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeocodeResult {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("sublocality")
    @Expose
    private String sublocality;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("type")
    @Expose
    private String type;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getSublocality() {
        return sublocality;
    }

    public void setSublocality(String sublocality) {
        this.sublocality = sublocality;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
