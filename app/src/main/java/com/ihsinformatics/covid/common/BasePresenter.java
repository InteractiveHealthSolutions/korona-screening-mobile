package com.ihsinformatics.covid.common;

import com.ihsinformatics.covid.model.Language;

public interface BasePresenter<T> {

    public void takeView(T view);

    public void dropView();

    Language getSelectedLangauge();

}
