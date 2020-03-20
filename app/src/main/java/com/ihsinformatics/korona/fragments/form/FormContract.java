package com.ihsinformatics.korona.fragments.form;

import com.ihsinformatics.korona.common.BasePresenter;
import com.ihsinformatics.korona.model.FormAnswer;
import com.ihsinformatics.korona.model.Question;

import java.util.List;

public interface FormContract {

    public interface View {

        void showResult(Integer totalScore);

        String getStringResource(int resId);
    }

    public interface Presenter extends BasePresenter<View> {
        List<Question> getQuestions();

        void updateScore(FormAnswer answer, Integer score, int section);

        void sendResult();
    }
}
