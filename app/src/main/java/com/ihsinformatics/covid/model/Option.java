package com.ihsinformatics.covid.model;

import com.ihsinformatics.covid.R;

public class Option {
    private Integer score;
    private OptionValue optionValue;

    public Option(Integer score, OptionValue optionValue) {
        this.score = score;
        this.optionValue = optionValue;
    }

    public enum OptionValue {

        YES("yes", R.string.yes),
        NO("no", R.string.no);

        private String shortName;
        private int textResId;


        OptionValue(String shortName, int textResId) {
            this.shortName = shortName;
            this.textResId = textResId;
        }

        public String getShortName() {
            return shortName;
        }

        public int getTextResId() {
            return textResId;
        }
    }

    public Integer getScore() {
        return score;
    }

    public OptionValue getOptionValue() {
        return optionValue;
    }
}
