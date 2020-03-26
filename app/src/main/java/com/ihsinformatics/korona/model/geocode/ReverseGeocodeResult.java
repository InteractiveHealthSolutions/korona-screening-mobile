package com.ihsinformatics.korona.model.geocode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReverseGeocodeResult {
    @SerializedName("results")
    @Expose
    private List<GeocodeResult> results = null;

    public List<GeocodeResult> getResults() {
        return results;
    }

    public void setResults(List<GeocodeResult> results) {
        this.results = results;
    }
}
