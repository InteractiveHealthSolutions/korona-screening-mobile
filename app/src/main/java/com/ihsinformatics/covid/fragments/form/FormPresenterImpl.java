package com.ihsinformatics.covid.fragments.form;


import com.ihsinformatics.covid.model.Language;
import com.ihsinformatics.covid.network.ApiService;

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

    @Override
    public Language getSelectedLangauge() {
        return null; //TODO working on it
    }


}
