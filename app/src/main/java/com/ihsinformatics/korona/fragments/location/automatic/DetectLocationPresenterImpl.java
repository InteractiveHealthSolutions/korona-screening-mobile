package com.ihsinformatics.korona.fragments.location.automatic;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.ihsinformatics.korona.activities.LoginActivity;
import com.ihsinformatics.korona.common.DevicePreferences;
import com.ihsinformatics.korona.common.IDGenerator;
import com.ihsinformatics.korona.common.Utils;
import com.ihsinformatics.korona.db.AppDatabase;
import com.ihsinformatics.korona.model.BaseResponse;
import com.ihsinformatics.korona.model.FailureStatus;
import com.ihsinformatics.korona.model.Language;
import com.ihsinformatics.korona.model.geocode.GeocodeResult;
import com.ihsinformatics.korona.model.geocode.ReverseGeocodeResult;
import com.ihsinformatics.korona.model.question.QuizResponse;
import com.ihsinformatics.korona.network.ResponseListener;
import com.ihsinformatics.korona.network.RestServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetectLocationPresenterImpl implements DetectLocationContract.Presenter {


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 21;
    public static final int REQUEST_LOCATION = 312;
    private Context context;
    private RestServices restServices;
    private DevicePreferences devicePreferences;
    private AppDatabase appdatabase;
    private DetectLocationContract.View view;

    public DetectLocationPresenterImpl(RestServices restServices, DevicePreferences devicePreferences, AppDatabase appdatabase) {
        this.restServices = restServices;
        this.devicePreferences = devicePreferences;
        this.appdatabase = appdatabase;
    }


    @Override
    public void takeView(DetectLocationContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        view = null;
    }

    @Override
    public Language getSelectedLangauge() {
        return devicePreferences.getLanguage();
    }


    @Override
    public void getUserLocation(Activity activity) {
        createLocationRequest(activity);
    }

    private void getLocationAddress(Location location) {
        view.showLoading();
        restServices.getLocationAddress(location, new ResponseListener.GeoCodeResponse() {
            @Override
            public void onSuccess(ReverseGeocodeResult response) {
                List<GeocodeResult> results = response.getResults();
                if (!results.isEmpty()) {
                    devicePreferences.saveGeoCode(results.get(0));
                    view.updateLocation(results.get(0));
                    view.hideLoading();
                } else {
                    view.showLocationError();
                }
            }

            @Override
            public void onFailure(String message) {
                view.showLocationError();
                view.showToast(message);
            }
        });
    }


    private void getMetaDataOnLocation(GeocodeResult geocodeResult) {
        // TODO  RestServices.
        view.showToast(geocodeResult.getRegion());
    }


    @Override
    public void checkPermission(LoginActivity activity) {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                view.showLocationReasonDialog();
            } else {
                requestPermission(activity);
            }
        } else {
            // Permission has already been granted
            getUserLocation(activity);
        }
    }

    @Override
    public void requestPermission(LoginActivity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);
    }

    @Override
    public List<com.ihsinformatics.korona.db.entities.Location> getCountries() {
        //appdatabase.getLocationDao().getLocationByCategory()
        List<com.ihsinformatics.korona.db.entities.Location> locations = new ArrayList<>();
        //locations.add(new com.ihsinformatics.korona.db.entities.Location(1, "Pakistan"));
        locations.add(new com.ihsinformatics.korona.db.entities.Location(1, "Australia"));
        return locations;
    }

    @Override
    public List<com.ihsinformatics.korona.db.entities.Location> getStates() {
        return appdatabase.getLocationDao().getAllLocation();
    }

    @Override
    public GeocodeResult getLastLocation() {
        return devicePreferences.getGeoCode();
    }

    protected void createLocationRequest(final Activity activity) {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(activity);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());


        task.addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                fetchLocationDetails(activity);
            }
        });

        task.addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        // Show the dialog by calling startResolutionForResult(),
                        // and check the result in onActivityResult().
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(activity,
                                REQUEST_LOCATION);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }
            }
        });


    }

    @Override
    public void fetchLocationDetails(Activity activity) {
        FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(activity);
        locationClient.getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    getLocationAddress(location);
                }
                else {
                    view.showLocationError();
                }
            }
        });


    }

    @Override
    public void syncLocations() {
        restServices.getLocations(new ResponseListener.LocationListener() {
            @Override
            public void onSuccess(List<com.ihsinformatics.korona.db.entities.Location> response) {
                if (!response.isEmpty()) {
                    appdatabase.getLocationDao().saveAllLocation(response);
                    view.setAdapter(response);
                    view.showLocationLayout();
                } else {
                    view.showToast("No data found, Please refresh again");
                    view.toggleRefresh(View.VISIBLE, FailureStatus.FETCHING_LOCATION);
                }
            }

            @Override
            public void onFailure(String message) {
                view.showToast(message);
                view.toggleRefresh(View.VISIBLE, FailureStatus.FETCHING_LOCATION);
            }
        });
    }

    @Override
    public com.ihsinformatics.korona.db.entities.Location getLocationFromName(String name) {
        return appdatabase.getLocationDao().getLocationByName(name);
    }

    @Override
    public void fetchForm(com.ihsinformatics.korona.db.entities.Location location) {
        restServices.fetchForm(location.getLocationId(), new ResponseListener.FetchFormListener() {
            @Override
            public void onSuccess(QuizResponse response) {
                view.startMainActivity(response);
            }

            @Override
            public void onFailure(String message) {
                view.toggleRefresh(View.VISIBLE, FailureStatus.FETCHING_FORM);
            }

            @Override
            public void responseCode(int code) {

            }
        });
    }

    @Override
    public void submitFormRequestForm(GeocodeResult geocodeResult) {
        if (geocodeResult != null) {
            JSONObject object = new JSONObject();
            try {

                JSONObject data = new JSONObject();
                data.put("country", geocodeResult.getCountry());
                data.put("state", geocodeResult.getRegion());
                object.put("data", data.toString());
                object.put("formDate", Utils.getCurrentDBDate());
                JSONObject formType = new JSONObject();

                formType.put("formTypeId", 2);
                object.put("formType", formType);
                object.put("referenceId", IDGenerator.getEncodedID());


            } catch (JSONException e) {
                e.printStackTrace();
            }

            restServices.submitForm(object, new ResponseListener.BaseListener() {
                @Override
                public void onSuccess(BaseResponse response) {
                    view.showToast("Thank you! Your request has been submitted.");
                }

                @Override
                public void onFailure(String message) {
                    view.showToast("Thank you!");
                }
            });

        }
    }


}
