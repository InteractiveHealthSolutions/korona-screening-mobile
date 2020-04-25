package com.ihsinformatics.korona.model.partners;

public class BasePartners {

    private String name;
    private String imageUrl;
    private String description;
    private String url;

    public BasePartners(String name, String imageUrl, String description, String url) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
