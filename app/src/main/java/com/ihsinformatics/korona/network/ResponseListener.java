package com.ihsinformatics.korona.network;

import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.model.BaseResponse;
import com.ihsinformatics.korona.model.geocode.GeocodeResult;
import com.ihsinformatics.korona.model.geocode.ReverseGeocodeResult;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.util.List;

public interface ResponseListener {

    public interface GeoCodeResponse{
        public void onSuccess(ReverseGeocodeResult response);
        public void onFailure(String message);
    }

    public interface LocationListener {
        public void onSuccess(List<Location> response);
        public void onFailure(String message);

    }

    public interface BaseListener {
        public void onSuccess(BaseResponse response);
        public void onFailure(String message);
    }

    public interface FetchFormListener {
        public void onSuccess(QuizResponse response);
        public void onFailure(String message);
        public void responseCode(int code);
    }
}
