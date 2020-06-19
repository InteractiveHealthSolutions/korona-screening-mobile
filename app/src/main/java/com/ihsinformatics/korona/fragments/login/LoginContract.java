package com.ihsinformatics.korona.fragments.login;

import android.app.Activity;

import com.ihsinformatics.korona.activities.LoginActivity;
import com.ihsinformatics.korona.common.BasePresenter;
import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.model.FailureStatus;
import com.ihsinformatics.korona.model.geocode.GeocodeResult;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.util.List;


public interface LoginContract {

    interface View {
        void showToast(String Message);

        void startMainActivity(QuizResponse response);

        void showLoading();

        void hideLoading();


        void showLocationReasonDialog();

        void updateLocation(GeocodeResult geocodeResult);

        void toggleRefresh(int visibility, FailureStatus status);

        void showLocationLayout();

        void setAdapter(List<Location> response);

        void showNoFormFound();

        void startTabbedActivity(List<QuizResponse> response);
    }


    interface Presenter extends BasePresenter<View> {
        void getUserLocation(Activity activity);

        void checkPermission(LoginActivity activity);

        void requestPermission(LoginActivity activity);

        List<Location> getCountries();

        List<Location> getStates();

        GeocodeResult getLastLocation();

        void fetchLocationDetails(Activity activity);

        void syncLocations();

        Location getLocationFromName(String region);

        void fetchForm(Location location);

        void submitFormRequestForm(GeocodeResult geocodeResult);

        void fetchFormTypes(Location selectedLocation);
    }

}
