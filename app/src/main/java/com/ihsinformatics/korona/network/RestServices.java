package com.ihsinformatics.korona.network;

import android.location.Location;

import com.ihsinformatics.korona.BuildConfig;
import com.ihsinformatics.korona.model.BaseResponse;
import com.ihsinformatics.korona.model.geocode.ReverseGeocodeResult;
import com.ihsinformatics.korona.model.question.QuizResponse;

import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import okhttp3.Credentials;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestServices {

    private ApiService apiService;
    private RapidApiService rapidApiService;
    private String authtoken = Credentials.basic(BuildConfig.USER_NAME, BuildConfig.PASSWORD);

    public RestServices(ApiService apiService, RapidApiService rapidApiService) {
        this.apiService = apiService;
        this.rapidApiService = rapidApiService;
    }

    public void getLocationAddress(Location location, final ResponseListener.GeoCodeResponse listener) {
        String coordinates = location.getLatitude() + "," + location.getLongitude();
        rapidApiService.getLocationList(coordinates, "en").enqueue(new Callback<ReverseGeocodeResult>() {
            @Override
            public void onResponse(Call<ReverseGeocodeResult> call, Response<ReverseGeocodeResult> response) {
                if (response != null) {
                    if (response.isSuccessful() && response.body() != null)
                        listener.onSuccess(response.body());
                    else
                        listener.onFailure(getErrorMessage(response.code()));
                } else {
                    listener.onFailure("No Response from server");
                }
            }

            @Override
            public void onFailure(Call<ReverseGeocodeResult> call, Throwable t) {
                listener.onFailure(getErrorMessage(t));
            }
        });
    }


    public void getLocations(final ResponseListener.LocationListener listener) {
        apiService.getLocations(authtoken).enqueue(new Callback<List<com.ihsinformatics.korona.db.entities.Location>>() {
            @Override
            public void onResponse(Call<List<com.ihsinformatics.korona.db.entities.Location>> call, Response<List<com.ihsinformatics.korona.db.entities.Location>> response) {
                if (response != null) {
                    if (response.isSuccessful() && response.body() != null)
                        listener.onSuccess(response.body());
                    else {
                        listener.onFailure(getErrorMessage(response.code()));
                    }
                } else {
                    listener.onFailure("No Response from server");
                }
            }

            @Override
            public void onFailure(Call<List<com.ihsinformatics.korona.db.entities.Location>> call, Throwable t) {
                listener.onFailure(getErrorMessage(t));
            }
        });
    }

    public void fetchForm(Integer locationId, final ResponseListener.FetchFormListener listener) {
        apiService.fetchForm(authtoken, locationId).enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response != null) {
                    if (response.isSuccessful() && response.body() != null)
                        listener.onSuccess(response.body());
                    else {
                        listener.onFailure(getErrorMessage(response.code()));
                        listener.responseCode(response.code());
                    }
                } else {
                    listener.onFailure("No Response from server");
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                listener.onFailure(getErrorMessage(t));
            }
        });

    }


    private String getErrorMessage(Throwable t) {
        String message = "";
        if (t instanceof SocketTimeoutException || t instanceof TimeoutException) {
            message = "Oops something went wrong";
        } else {
            message = "Please check your internet connection...";
        }
        return message;
    }

    private String getErrorMessage(int code) {
        String message = "";
        switch (code) {
            case 401:
                message = "You are not authorized, Please login with the correct username/password";
                break;
            case 403:
                message = "You don't have privilege/role to access this service";
                break;
            case 404:
                message = "No data exist";
                break;
            case 405:
                message = "Method not allowed";
                break;
            case 406:
                message = "the data you are sending is not acceptable";
                break;
            default:
                message = "Something went wrong";
                break;

        }

        return message;
    }


    public void submitForm(JSONObject object, final ResponseListener.BaseListener listener) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), object.toString());

        apiService.submitForm(authtoken, body).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response != null) {
                    if (response.isSuccessful() && response.body() != null)
                        listener.onSuccess(response.body());
                    else
                        listener.onFailure(getErrorMessage(response.code()));
                } else {
                    listener.onFailure("No Response from server");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                listener.onFailure(getErrorMessage(t));
            }
        });
    }

    public void fetchFormByLocationName(String name, final ResponseListener.FetchFormListener listener) {
        apiService.fetchFormByLocationName(authtoken, name).enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response != null) {
                    if (response.isSuccessful() && response.body() != null)
                        listener.onSuccess(response.body());
                    else {
                        listener.onFailure(getErrorMessage(response.code()));
                        listener.responseCode(response.code());
                    }
                } else {
                    listener.onFailure("No Response from server");
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                listener.onFailure(getErrorMessage(t));
            }
        });
    }
}
