package com.ihsinformatics.korona.fragments.form;

import android.content.Intent;

import com.ihsinformatics.korona.common.BasePresenter;
import com.ihsinformatics.korona.model.FormAnswer;
import com.ihsinformatics.korona.model.question.Location;
import com.ihsinformatics.korona.model.question.Questions;

import java.util.List;

public interface FormContract {

    public interface View {

        void showResult(Integer totalScore);

        String getStringResource(int resId);
    }

    public interface Presenter extends BasePresenter<View> {
        List<Questions> getQuestions(Intent intent);
        Location getLocation();

        void updateScore(FormAnswer answer);

        void sendResult();
    }
}
