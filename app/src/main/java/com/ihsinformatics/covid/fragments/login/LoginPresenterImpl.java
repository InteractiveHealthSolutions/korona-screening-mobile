package com.ihsinformatics.covid.fragments.login;

import android.content.Context;

import com.ihsinformatics.covid.common.DevicePreferences;
import com.ihsinformatics.covid.model.Language;
import com.ihsinformatics.covid.network.ApiService;

import java.util.ArrayList;
import java.util.List;

public class LoginPresenterImpl implements LoginContract.Presenter {


    private Context context;
    private ApiService apiService;
    private DevicePreferences devicePreferences;
    private LoginContract.View view;

    public LoginPresenterImpl(Context context, ApiService apiService, DevicePreferences devicePreferences) {
        this.context = context;
        this.apiService = apiService;
        this.devicePreferences = devicePreferences;
    }

    @Override
    public void login(String username, String user) {
        view.startMainActivity();

    }

    @Override
    public void saveLanguage(Language language) {
        devicePreferences.saveLanguage(language);
    }

    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public Language getSelectedLangauge() {
        return devicePreferences.getLanguage();
    }

    @Override
    public List<Language> getLanguages() {
        List<Language> languages = new ArrayList<>();
        languages.add(new Language("English", "en"));
        languages.add(new Language("اردو", "ur"));
        languages.add(new Language("سنڌي", "sd"));
        return languages;
    }

    @Override
    public Language getLanguageFromList(String name) {
        Language languageValue = null;
        for (Language language : getLanguages()) {
            if (language.getLanguage().equals(name)) {
                languageValue = language;
                break;
            }
        }
        return languageValue;
    }

}
