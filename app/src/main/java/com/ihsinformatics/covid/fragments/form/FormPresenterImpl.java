package com.ihsinformatics.covid.fragments.form;


import com.ihsinformatics.covid.R;
import com.ihsinformatics.covid.common.IDGenerator;
import com.ihsinformatics.covid.common.Utils;
import com.ihsinformatics.covid.model.BaseResponse;
import com.ihsinformatics.covid.model.FormAnswer;
import com.ihsinformatics.covid.model.Language;
import com.ihsinformatics.covid.model.Option;
import com.ihsinformatics.covid.model.Question;
import com.ihsinformatics.covid.network.ApiService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPresenterImpl implements FormContract.Presenter {

    private final ApiService apiService;
    private FormContract.View view;
    private List<FormAnswer> formAnswers = new ArrayList<>();
    private Integer totalScore = 0;
    private int lastSection = 0;

    public FormPresenterImpl(ApiService apiService) {
        this.apiService = apiService;
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
    public void updateScore(FormAnswer forAnswer, Integer score, int section) {
        formAnswers.add(forAnswer);
        if (score > 0 && section != lastSection) {
            totalScore += score;
            lastSection = section;
        }
    }

    @Override
    public void sendResult() {
        JSONObject object = new JSONObject();
        try {

            JSONObject data = getData();
            data.put("total_score", totalScore);
            object.put("data", data.toString());
            object.put("formDate", Utils.getCurrentDBDate());
            JSONObject formType = new JSONObject();
            formType.put("formTypeId", 1);
            object.put("formType", formType);
            object.put("referenceId", IDGenerator.getEncodedID());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        view.showResult(totalScore);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), object.toString());

        apiService.submitForm(body).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                view.showResult(totalScore);
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                view.showResult(totalScore);
            }
        });
    }

    private JSONObject getData() throws JSONException {
        JSONObject base = new JSONObject();
        for (FormAnswer formAnswer : formAnswers) {
            base.put(formAnswer.getQuestionId(), formAnswer.getAnswer().getOptionValue().getShortName());
        }
        return base;
    }


    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(1, "Exposure", "recent_travel", view.getStringResource(R.string.sec_1_question_1), Arrays.asList(new Option(2, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_plane));
        questions.add(new Question(1, "Exposure", "contact_traveller", view.getStringResource(R.string.sec_1_question_2), Arrays.asList(new Option(2, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_1b));
        questions.add(new Question(1, "Exposure", "contact_patient", view.getStringResource(R.string.sec_1_question_3), Arrays.asList(new Option(2, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_1c));
        questions.add(new Question(1, "Exposure", "household_member_patient", view.getStringResource(R.string.sec_1_question_4), Arrays.asList(new Option(2, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_1d));

        questions.add(new Question(2, "Symptoms", "fever", view.getStringResource(R.string.sec_2_question_1), Arrays.asList(new Option(1, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_fever));

        questions.add(new Question(3, "Symptoms", "cough", view.getStringResource(R.string.sec_3_question_1), Arrays.asList(new Option(1, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_3a));
        questions.add(new Question(3, "Symptoms", "cold", view.getStringResource(R.string.sec_3_question_2), Arrays.asList(new Option(1, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_cold));
        questions.add(new Question(3, "Symptoms", "sore_throat", view.getStringResource(R.string.sec_3_question_3), Arrays.asList(new Option(1, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_sore_throat));
        questions.add(new Question(3, "Symptoms", "sob", view.getStringResource(R.string.sec_3_question_4), Arrays.asList(new Option(1, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_shortness_of_breath));

        return questions;
    }
}
