package com.ihsinformatics.covid.adapter;

import com.ihsinformatics.covid.model.Option;
import com.ihsinformatics.covid.model.Question;


public interface AdapterListener {

    public interface OptionClickedListener {
        public void onOptionClicked(Question question, Option option);
        public void onInfoClicked();
    }
}
