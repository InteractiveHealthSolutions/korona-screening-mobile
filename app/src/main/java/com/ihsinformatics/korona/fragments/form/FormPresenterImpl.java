package com.ihsinformatics.korona.fragments.form;


import android.content.Intent;

import com.google.gson.Gson;
import com.ihsinformatics.korona.common.IDGenerator;
import com.ihsinformatics.korona.common.Utils;
import com.ihsinformatics.korona.model.BaseResponse;
import com.ihsinformatics.korona.model.FormAnswer;
import com.ihsinformatics.korona.model.Language;
import com.ihsinformatics.korona.model.question.Location;
import com.ihsinformatics.korona.model.question.Questions;
import com.ihsinformatics.korona.model.question.QuizResponse;
import com.ihsinformatics.korona.network.ResponseListener;
import com.ihsinformatics.korona.network.RestServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.ihsinformatics.korona.activities.LoginActivity.FORM;

public class FormPresenterImpl implements FormContract.Presenter {


    private FormContract.View view;
    private List<FormAnswer> formAnswers = new ArrayList<>();
    private Integer totalScore = 0;
    private RestServices restServices;
    private QuizResponse quizResponse;

    public FormPresenterImpl(RestServices restServices) {
        this.restServices = restServices;

    }


    @Override
    public void takeView(FormContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {

    }

    @Override
    public Language getSelectedLangauge() {
        return null; //TODO working on it
    }


    @Override
    public void updateScore(FormAnswer forAnswer, Integer score) {
        formAnswers.add(forAnswer);
        totalScore += score;
    }

    @Override
    public void sendResult() {
        JSONObject object = new JSONObject();
        try {

            JSONObject data = getData();
            data.put("total_score", totalScore);
            object.put("data", data.toString());
            object.put("formDate", Utils.getCurrentDBDate());
            object.put("location", new JSONObject(new Gson().toJson(quizResponse.getLocation())));
            JSONObject formType = new JSONObject();
            formType.put("formTypeId", 1);
            object.put("activityId ", quizResponse.getActivityId());
            object.put("formType", formType);
            object.put("referenceId", IDGenerator.getEncodedID());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        restServices.submitForm(object, new ResponseListener.BaseListener() {
            @Override
            public void onSuccess(BaseResponse response) {
                //TODO show results
            }

            @Override
            public void onFailure(String message) {
                //TODO ask for resubmitting
            }
        });


        view.showResult(getResults());
    }

    @Override
    public int getPositionFromQuesionId(Integer questionId) {

        int arrayPosition = 0;
        for (int i = 0; i < quizResponse.getQuestions().size(); i++) {
            if (questionId.equals(quizResponse.getQuestions().get(i).getQuestionId())) {
                arrayPosition = i;
                break;
            }

        }
        return arrayPosition;
    }

    @Override
    public String getActivityDescription() {
        return quizResponse.getDescription();
    }

    private String getResults() {
        String result = "";
        try {
            JSONObject jsonObject = new JSONObject(quizResponse.getDecision());
            if (jsonObject.has("" + totalScore))
                result = jsonObject.getString("" + totalScore);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private JSONObject getData() throws JSONException {
        JSONObject base = new JSONObject();
        for (FormAnswer formAnswer : formAnswers) {
            base.put(formAnswer.getQuestionId(), formAnswer.getAnswer());
        }
        return base;
    }


    @Override
    public List<Questions> getQuestions(Intent intent) {
        final String data = intent.getExtras().getString(FORM);
        quizResponse = new Gson().fromJson(data, QuizResponse.class);
        return quizResponse.getQuestions();
    }



    @Override
    public int getFirstQuestionPoistion() {
        Integer questionId = Integer.valueOf(quizResponse.getQuestionsOrder());
        return getPositionFromQuesionId(questionId);
    }

    @Override
    public Location getLocation() {
        return quizResponse.getLocation();
    }


}
