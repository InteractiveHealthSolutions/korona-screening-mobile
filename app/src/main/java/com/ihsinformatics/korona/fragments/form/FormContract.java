package com.ihsinformatics.korona.fragments.form;

import android.content.Intent;

import com.ihsinformatics.korona.common.BasePresenter;
import com.ihsinformatics.korona.model.FormAnswer;
import com.ihsinformatics.korona.model.question.Location;
import com.ihsinformatics.korona.model.question.Questions;

import java.util.List;

public interface FormContract {

    public interface View {

        void showResult(String result);

        String getStringResource(int resId);

        void moveAdapterToPosition(int arrayPosition);
    }

    public interface Presenter extends BasePresenter<View> {
        List<Questions> getQuestions(Intent intent);
        Location getLocation();
        int getFirstQuestionPosition();

        void updateScore(Integer score);

        void sendResult();

        int getPositionFromQuesionId(Integer nextQuestion);

        String getActivityDescription();

        void addAnswer(FormAnswer formAnswer);
    }
}
