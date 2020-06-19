package com.ihsinformatics.korona.network;



import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.model.BaseResponse;
import com.ihsinformatics.korona.model.form.FormTypeResponse;
import com.ihsinformatics.korona.model.partners.BasePartners;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST(Endpoints.FORM_DATA)
    Call<BaseResponse> submitForm(@Header("Authorization") String auth,@Body RequestBody body);

    @GET(Endpoints.LOCATION_LIST)
    Call<List<Location>> getLocations(@Header("Authorization") String auth);

    @GET(Endpoints.PARTNERS)
    Call<List<BasePartners>> getPartners(@Header("Authorization") String auth);

    @GET(Endpoints.FETCH_FORM)
    Call<QuizResponse> fetchForm(@Header("Authorization") String auth,@Path(value = "id") Integer id);

    @GET(Endpoints.FORM_TYPE_BY_LOCATION)
    Call<List<FormTypeResponse>> fetchFormType(@Header("Authorization") String auth, @Path(value = "id") Integer id);

    @GET(Endpoints.FETCH_FORM_BY_NAME)
    Call<QuizResponse> fetchFormByLocationName(@Header("Authorization") String auth,@Path(value = "name") String name);

    @GET(Endpoints.FETCH_ACTIVITIES_BY_NAME)
    Call<List<QuizResponse>> fetchActivitiesByName(@Header("Authorization") String auth,@Path(value = "name") String name);
}
