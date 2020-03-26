package com.ihsinformatics.korona.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RapidApiClient {

    private OkHttpClient okHttpClient;

    public RapidApiClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public RapidApiService getRapidApiClient() {

        Retrofit retrofitClient = new Retrofit.Builder()
                .baseUrl("https://trueway-geocoding.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        return retrofitClient.create(RapidApiService.class);
    }
}
