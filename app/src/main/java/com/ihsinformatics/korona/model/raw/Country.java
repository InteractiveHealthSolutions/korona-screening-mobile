package com.ihsinformatics.korona.model.raw;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Country {
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("states")
    @Expose
    private List<String> states = null;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return country;
    }
}
