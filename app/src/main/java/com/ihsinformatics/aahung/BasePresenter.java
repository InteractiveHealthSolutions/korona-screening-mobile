package com.ihsinformatics.aahung;

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();

}
