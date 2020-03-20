package com.ihsinformatics.korona.common;

import com.ihsinformatics.korona.model.Language;

public interface BasePresenter<T> {

    public void takeView(T view);

    public void dropView();

    Language getSelectedLangauge();

}
