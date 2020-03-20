package com.ihsinformatics.korona.network;



import com.ihsinformatics.korona.model.BaseResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("formdata")
    Call<BaseResponse> submitForm(@Body RequestBody body);
}
