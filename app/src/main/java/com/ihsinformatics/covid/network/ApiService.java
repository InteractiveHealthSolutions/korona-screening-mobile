package com.ihsinformatics.covid.network;



import com.ihsinformatics.covid.model.Language;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("users/{user}/repos")
    Call<Language> listRepos(@Path("user") String user);
}
