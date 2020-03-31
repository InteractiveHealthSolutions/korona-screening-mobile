package com.ihsinformatics.korona.adapter;


import com.ihsinformatics.korona.model.FormAnswer;
import com.ihsinformatics.korona.model.question.Option;
import com.ihsinformatics.korona.model.question.Questions;


public interface AdapterListener {

    public interface OptionClickedListener {

        public void onInfoClicked();

        public void onOptionClicked(Questions question, Option option);
    }
}
