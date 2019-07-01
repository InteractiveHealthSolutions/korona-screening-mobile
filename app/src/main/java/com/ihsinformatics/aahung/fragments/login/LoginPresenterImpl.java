package com.ihsinformatics.aahung.fragments.login;

import com.ihsinformatics.aahung.model.Repo;
import com.ihsinformatics.aahung.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenterImpl implements LoginContract.Presenter {

    private ApiService apiService;
    private LoginContract.View view;

    public LoginPresenterImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void login(String user) {
        apiService.listRepos(user).enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.showToast("success" + response.body().get(0).getFullName());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                view.showToast(t.getMessage());
            }
        });

    }

    @Override
    public void takeView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {

    }
}
