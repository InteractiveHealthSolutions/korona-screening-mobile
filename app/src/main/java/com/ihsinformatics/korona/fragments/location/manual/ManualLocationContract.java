package com.ihsinformatics.korona.fragments.location.manual;

import android.app.Activity;

import com.ihsinformatics.korona.activities.LoginActivity;
import com.ihsinformatics.korona.common.BasePresenter;
import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.model.FailureStatus;
import com.ihsinformatics.korona.model.geocode.GeocodeResult;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.io.InputStream;
import java.util.List;


public interface ManualLocationContract {

    interface View {
        void showToast(String Message);

        void startMainActivity(QuizResponse response);

        void showLoading();

        void hideLoading();



        void showLocationReasonDialog();

        void updateLocation(GeocodeResult geocodeResult);

        void toggleRefresh(int visibility, FailureStatus status);

        void showLocationLayout();

        void setAdapter(List<Location> countries, List<Location> states);
    }


    interface Presenter extends BasePresenter<View> {
        void getUserLocation(Activity activity);

        void createLocationsJson(InputStream inputStream);

        void checkPermission(LoginActivity activity);

        void requestPermission(LoginActivity activity);

        String getCountriesJSON();

        List<Location> getStates();

        GeocodeResult getLastLocation();

        void fetchLocationDetails(Activity activity);

        void syncLocations();

        Location getLocationFromName(String region);

        void fetchForm(Location location);

        void submitFormRequestForm(String country,String state);

        List<Location> getLocationsByParent(Integer parentLocationId);

        boolean isLocationRequestSubmitted();
    }

}
