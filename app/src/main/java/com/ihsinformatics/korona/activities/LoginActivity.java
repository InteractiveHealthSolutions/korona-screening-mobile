package com.ihsinformatics.korona.activities;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.databinding.ActivityLoginBinding;
import com.ihsinformatics.korona.fragments.login.LoginContract;
import com.ihsinformatics.korona.model.Language;
import com.ihsinformatics.korona.network.ApiService;

import javax.inject.Inject;

import lib.kingja.switchbutton.SwitchMultiButton;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Inject
    SharedPreferences preferences;

    @Inject
    ApiService apiService;

    @Inject
    LoginContract.Presenter presenter;


    ActivityLoginBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getComponent().inject(this);

        Language selectedLangauge = presenter.getSelectedLangauge();
        if (selectedLangauge != null)
            super.updateLocale(this, selectedLangauge.getLocale());

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        presenter.takeView(this);

        setLanguageAdapter();
        binding.language.setOnSwitchListener(new ActivityListener());
    }

    private void setLanguageAdapter() {

        Language selectedLangauge = presenter.getSelectedLangauge();
        if (selectedLangauge != null) {
            if (selectedLangauge.getLanguage().equals("English"))
                binding.language.setSelectedTab(0);
            else if (selectedLangauge.getLanguage().equals("اردو"))
                binding.language.setSelectedTab(1);
            else
                binding.language.setSelectedTab(2);
        }
    }


    @Override
    public void showToast(String Message) {
        Toast.makeText(this, Message , Toast.LENGTH_SHORT).show();
    }

    public void onLoginButtonClicked(View view) {
        if (binding.agreed.isChecked())
            startMainActivity();
        else
            showToast(getString(R.string.error_term_condition));
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    private class ActivityListener implements SwitchMultiButton.OnSwitchListener {
        @Override
        public void onSwitch(int position, String name) {
            Language language = presenter.getLanguageFromList(name);
            if (language != null) {
                presenter.saveLanguage(language);
                setLocale(language.getLocale());
            }
        }
    }

    private void setLocale(String languageCode) {
        super.updateLocale(this, languageCode);
        refreshActivity();
    }

    private void refreshActivity() {
        startActivity(new Intent(LoginActivity.this, LoginActivity.class));
        LoginActivity.this.finish();
    }

}