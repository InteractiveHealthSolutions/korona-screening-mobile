package com.ihsinformatics.covid.fragments.result;



import com.ihsinformatics.covid.common.BasePresenter;
import com.ihsinformatics.covid.model.Location;

import java.util.List;

public interface ResultContract {

    interface View {
        void showToast(String message);

        void setResultDetail(String details);

        String getStringResource(int resId);

        void setScoreColor(int resId);

        void showLocations(List<Location> locations);

        void showContact(List<String> contacts);
    }


    interface Presenter extends BasePresenter<View> {

        void setScore(int score);
    }
}
