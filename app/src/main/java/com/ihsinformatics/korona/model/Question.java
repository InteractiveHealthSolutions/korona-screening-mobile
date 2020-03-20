package com.ihsinformatics.korona.model;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    private int section;
    private String sectionName;
    private String shortName;
    private String question;
    private List<Option> options;
    private int icon;
    private boolean isCountriesAvailable;




    public Question(int section,String sectionName, String shortName, String question, List<Option> options, int icon) {
        this.section = section;
        this.sectionName = sectionName;
        this.shortName = shortName;
        this.question = question;
        this.options = options;
        this.icon = icon;
    }

    public boolean isCountriesAvailable() {
        return isCountriesAvailable;
    }

    public Question enableCountries() {
        isCountriesAvailable = true;
        return this;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getShortName() {
        return shortName;
    }


    public List<Option> getOptions() {
        return options;
    }

    public int getSection() {
        return section;
    }

    public int getIcon() {
        return icon;
    }

    public String getQuestion() {
        return question;
    }
}
