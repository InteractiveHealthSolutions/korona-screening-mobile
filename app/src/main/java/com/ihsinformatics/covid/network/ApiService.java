package com.ihsinformatics.covid.network;



import com.ihsinformatics.covid.model.BaseResponse;
import com.ihsinformatics.covid.model.Language;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("formdata")
    Call<BaseResponse> submitForm(@Body RequestBody body);
}
