package com.ihsinformatics.korona.adapter;


import com.ihsinformatics.korona.model.FormAnswer;
import com.ihsinformatics.korona.model.question.Option;
import com.ihsinformatics.korona.model.question.Questions;
import com.ihsinformatics.korona.model.question.QuizResponse;


public interface AdapterListener {

    public interface OptionClickedListener {

        public void onInfoClicked();

        public void onOptionClicked(Questions question, Option option);
    }

    public interface FormClickedListener {

        public void onFormClicked(QuizResponse activity);
    }
}
