package com.ihsinformatics.covid.fragments.login;

import com.ihsinformatics.covid.common.BasePresenter;
import com.ihsinformatics.covid.model.Language;

import java.util.List;


public interface LoginContract {

    interface View {
        void showToast(String Message);

        void startMainActivity();
    }


    interface Presenter extends BasePresenter<View> {
        void login(String username, String password);
        void saveLanguage(Language language);
        List<Language> getLanguages();


        Language getLanguageFromList(String name);
    }

}
