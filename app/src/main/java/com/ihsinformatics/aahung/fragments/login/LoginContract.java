package com.ihsinformatics.aahung.fragments.login;

import com.ihsinformatics.aahung.BasePresenter;

public interface LoginContract {

    interface View {
        void showToast(String Message);
    }


    interface Presenter extends BasePresenter<View> {
        void login(String name);
    }


}
