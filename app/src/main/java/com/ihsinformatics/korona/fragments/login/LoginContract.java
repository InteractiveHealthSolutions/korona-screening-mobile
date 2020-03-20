package com.ihsinformatics.korona.fragments.login;

import com.ihsinformatics.korona.common.BasePresenter;
import com.ihsinformatics.korona.model.Language;

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
