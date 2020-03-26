
package com.ihsinformatics.korona.model.question;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("isVoided")
    @Expose
    private Boolean isVoided;
    @SerializedName("createdBy")
    @Expose
    private CreatedBy createdBy;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("reasonVoided")
    @Expose
    private Object reasonVoided;
    @SerializedName("locationId")
    @Expose
    private Integer locationId;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("address1")
    @Expose
    private Object address1;
    @SerializedName("address2")
    @Expose
    private Object address2;
    @SerializedName("address3")
    @Expose
    private Object address3;
    @SerializedName("postalCode")
    @Expose
    private Object postalCode;
    @SerializedName("landmark1")
    @Expose
    private Object landmark1;
    @SerializedName("landmark2")
    @Expose
    private Object landmark2;
    @SerializedName("cityVillage")
    @Expose
    private Object cityVillage;
    @SerializedName("stateProvince")
    @Expose
    private Object stateProvince;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("primaryContact")
    @Expose
    private Object primaryContact;
    @SerializedName("primaryContactPerson")
    @Expose
    private Object primaryContactPerson;
    @SerializedName("secondaryContact")
    @Expose
    private Object secondaryContact;
    @SerializedName("secondaryContactPerson")
    @Expose
    private Object secondaryContactPerson;
    @SerializedName("tertiaryContact")
    @Expose
    private Object tertiaryContact;
    @SerializedName("tertiaryContactPerson")
    @Expose
    private Object tertiaryContactPerson;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = null;
    @SerializedName("parentLocation")
    @Expose
    private Object parentLocation;

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

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Object getAddress1() {
        return address1;
    }

    public void setAddress1(Object address1) {
        this.address1 = address1;
    }

    public Object getAddress2() {
        return address2;
    }

    public void setAddress2(Object address2) {
        this.address2 = address2;
    }

    public Object getAddress3() {
        return address3;
    }

    public void setAddress3(Object address3) {
        this.address3 = address3;
    }

    public Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    public Object getLandmark1() {
        return landmark1;
    }

    public void setLandmark1(Object landmark1) {
        this.landmark1 = landmark1;
    }

    public Object getLandmark2() {
        return landmark2;
    }

    public void setLandmark2(Object landmark2) {
        this.landmark2 = landmark2;
    }

    public Object getCityVillage() {
        return cityVillage;
    }

    public void setCityVillage(Object cityVillage) {
        this.cityVillage = cityVillage;
    }

    public Object getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(Object stateProvince) {
        this.stateProvince = stateProvince;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(Object primaryContact) {
        this.primaryContact = primaryContact;
    }

    public Object getPrimaryContactPerson() {
        return primaryContactPerson;
    }

    public void setPrimaryContactPerson(Object primaryContactPerson) {
        this.primaryContactPerson = primaryContactPerson;
    }

    public Object getSecondaryContact() {
        return secondaryContact;
    }

    public void setSecondaryContact(Object secondaryContact) {
        this.secondaryContact = secondaryContact;
    }

    public Object getSecondaryContactPerson() {
        return secondaryContactPerson;
    }

    public void setSecondaryContactPerson(Object secondaryContactPerson) {
        this.secondaryContactPerson = secondaryContactPerson;
    }

    public Object getTertiaryContact() {
        return tertiaryContact;
    }

    public void setTertiaryContact(Object tertiaryContact) {
        this.tertiaryContact = tertiaryContact;
    }

    public Object getTertiaryContactPerson() {
        return tertiaryContactPerson;
    }

    public void setTertiaryContactPerson(Object tertiaryContactPerson) {
        this.tertiaryContactPerson = tertiaryContactPerson;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public List<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    public Object getParentLocation() {
        return parentLocation;
    }

    public void setParentLocation(Object parentLocation) {
        this.parentLocation = parentLocation;
    }

}
