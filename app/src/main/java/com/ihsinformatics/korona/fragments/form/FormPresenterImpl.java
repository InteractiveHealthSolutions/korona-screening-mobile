package com.ihsinformatics.korona.fragments.form;


import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.common.IDGenerator;
import com.ihsinformatics.korona.common.Utils;
import com.ihsinformatics.korona.model.BaseResponse;
import com.ihsinformatics.korona.model.FormAnswer;
import com.ihsinformatics.korona.model.Language;
import com.ihsinformatics.korona.model.Option;
import com.ihsinformatics.korona.model.Question;
import com.ihsinformatics.korona.network.ApiService;

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

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), object.toString());

        apiService.submitForm(body).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                //Do Nothing
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                //Do Nothing
            }
        });

        view.showResult(totalScore);
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

        questions.add(new Question(1, view.getStringResource(R.string.question_title_exposure), "recent_travel", view.getStringResource(R.string.sec_1_question_1), Arrays.asList(new Option(2, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_plane).enableCountries());
        questions.add(new Question(1, view.getStringResource(R.string.question_title_exposure), "contact_traveller", view.getStringResource(R.string.sec_1_question_2), Arrays.asList(new Option(2, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_1b).enableCountries());
        questions.add(new Question(1, view.getStringResource(R.string.question_title_exposure), "contact_patient", view.getStringResource(R.string.sec_1_question_3), Arrays.asList(new Option(2, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_1c));
        questions.add(new Question(1, view.getStringResource(R.string.question_title_exposure), "household_member_patient", view.getStringResource(R.string.sec_1_question_4), Arrays.asList(new Option(2, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_1d));

        questions.add(new Question(2, view.getStringResource(R.string.question_title_symptoms), "fever", view.getStringResource(R.string.sec_2_question_1), Arrays.asList(new Option(1, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_fever));

        questions.add(new Question(3, view.getStringResource(R.string.question_title_symptoms), "cough", view.getStringResource(R.string.sec_3_question_1), Arrays.asList(new Option(1, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_3a));
        questions.add(new Question(3, view.getStringResource(R.string.question_title_symptoms), "cold", view.getStringResource(R.string.sec_3_question_2), Arrays.asList(new Option(1, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_cold));
        questions.add(new Question(3, view.getStringResource(R.string.question_title_symptoms), "sore_throat", view.getStringResource(R.string.sec_3_question_3), Arrays.asList(new Option(1, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_sore_throat));
        questions.add(new Question(3, view.getStringResource(R.string.question_title_symptoms), "sob", view.getStringResource(R.string.sec_3_question_4), Arrays.asList(new Option(1, Option.OptionValue.YES), new Option(0, Option.OptionValue.NO)), R.drawable.ic_shortness_of_breath));

        return questions;
    }
}
