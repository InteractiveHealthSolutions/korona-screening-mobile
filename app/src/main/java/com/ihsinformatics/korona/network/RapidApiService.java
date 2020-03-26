package com.ihsinformatics.korona.network;

import com.ihsinformatics.korona.model.BaseResponse;
import com.ihsinformatics.korona.model.geocode.ReverseGeocodeResult;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RapidApiService {

    @Headers({
            "x-rapidapi-host: trueway-geocoding.p.rapidapi.com",
            "x-rapidapi-key: 40adacf72cmsha8451041e1b86b1p102e6ejsn1a958d6e5295",
    })
    @GET("ReverseGeocode")
    Call<ReverseGeocodeResult> getLocationList(@Query(value = "location") String coordinates, @Query(value = "language") String language); //      sample 24.8607%252C67.0011

}
