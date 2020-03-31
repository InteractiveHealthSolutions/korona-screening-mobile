package com.ihsinformatics.korona.di.module;


import com.ihsinformatics.korona.BuildConfig;
import com.ihsinformatics.korona.network.ApiService;
import com.ihsinformatics.korona.network.RapidApiClient;
import com.ihsinformatics.korona.network.RapidApiService;
import com.ihsinformatics.korona.network.RestServices;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {


    @Provides
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    public Retrofit provideRetrofitClient(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }


    @Provides
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    public RapidApiService provideRapidApi(OkHttpClient okHttpClient) {
        return new RapidApiClient(okHttpClient).getRapidApiClient();
    }

    @Provides
    public RestServices provideRestService(ApiService apiService,RapidApiService rapidApiService) {
        return new RestServices(apiService,rapidApiService);
    }
}
