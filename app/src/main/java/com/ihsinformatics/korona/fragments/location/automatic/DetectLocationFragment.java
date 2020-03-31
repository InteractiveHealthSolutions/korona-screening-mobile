package com.ihsinformatics.korona.fragments.location.automatic;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.activities.LoginActivity;
import com.ihsinformatics.korona.databinding.FragmentDetectLocationBinding;
import com.ihsinformatics.korona.databinding.FragmentDetectLocationBindingImpl;
import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.model.FailureStatus;
import com.ihsinformatics.korona.model.geocode.GeocodeResult;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetectLocationFragment extends Fragment implements DetectLocationContract.View, View.OnClickListener, DialogInterface.OnClickListener {

    private GeocodeResult geocodeResult;

    public DetectLocationFragment() {
        // Required empty public constructor
    }

    @Inject
    DetectLocationContract.Presenter presenter;

    FragmentDetectLocationBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detect_location, container, false);
        presenter.takeView(this);
        init();

        if (presenter.getLastLocation() != null) {
            updateLocation(presenter.getLastLocation());

        } else {
            presenter.checkPermission((LoginActivity) getActivity());
        }
        return binding.getRoot();
    }

    private void init() {
        binding.detectLocation.setOnClickListener(this);
        binding.manualLocation.setOnClickListener(this);
        binding.manualLocationOther.setOnClickListener(this);
        binding.nextOther.setOnClickListener(this);

    }

    @Override
    public void showToast(String Message) {
        Toast.makeText(getActivity(), Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startMainActivity(QuizResponse response) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        binding.loading.layoutLoading.setVisibility(View.GONE);
    }

    @Override
    public void showLocationReasonDialog() {
        AlertDialog alertDialog = new MaterialAlertDialogBuilder(getActivity())
                .setTitle("Location Required")
                .setMessage("This app required location to show the details on the basis of your location")
                .setCancelable(false)
                .setIcon(R.drawable.ic_location)
                .setNeutralButton("OK", this)
                .create();
        alertDialog.show();
    }

    @Override
    public void updateLocation(GeocodeResult geocodeResult) {
        this.geocodeResult = geocodeResult;
        if (geocodeResult != null) {
            binding.countryLayout.setVisibility(View.VISIBLE);
            binding.countryLabel.setText(geocodeResult.getCountry());
            binding.stateLabel.setText(geocodeResult.getRegion());
            binding.loading.layoutLoading.setVisibility(View.GONE);
        }
    }

    @Override
    public void toggleRefresh(int visibility, FailureStatus status) {

    }

    @Override
    public void showLocationLayout() {

    }

    @Override
    public void setAdapter(List<Location> response) {

    }

    @Override
    public void showLocationError() {
        binding.loading.layoutLoading.setVisibility(View.GONE);
        binding.layoutError.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View view) {
        if (view.equals(binding.detectLocation)) {
            presenter.requestPermission((LoginActivity)getActivity());
            presenter.getUserLocation(getActivity());
        } else if (view.equals(binding.manualLocation) || view.equals(binding.manualLocationOther)) {
            ((LoginActivity) getActivity()).changePage(1);
        }
        else if (view.equals(binding.nextOther)) {
            ((LoginActivity) getActivity()).changePage(2);
            ((LoginActivity) getActivity()).setSelectedLocation(new Location(-1,geocodeResult.getRegion()));
        }
    }

    public void fetchLocation() {
        presenter.fetchLocationDetails(getActivity());
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        presenter.requestPermission((LoginActivity) getActivity());
    }

    public void getUserLocation() {
        presenter.getUserLocation((LoginActivity) getActivity());

    }
}
