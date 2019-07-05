package com.ihsinformatics.aahung.fragments.form;


import com.ihsinformatics.aahung.network.ApiService;
import com.ihsinformatics.aahung.views.FormBuilder;

public class FormPresenterImpl implements FormContract.Presenter {

    private final ApiService apiService;
    private FormContract.View view;

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



}
