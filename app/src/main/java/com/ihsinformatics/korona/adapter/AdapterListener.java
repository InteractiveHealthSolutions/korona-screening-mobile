package com.ihsinformatics.korona.adapter;

import com.ihsinformatics.korona.model.Option;
import com.ihsinformatics.korona.model.Question;


public interface AdapterListener {

    public interface OptionClickedListener {
        public void onOptionClicked(Question question, Option option);
        public void onInfoClicked();
    }
}
