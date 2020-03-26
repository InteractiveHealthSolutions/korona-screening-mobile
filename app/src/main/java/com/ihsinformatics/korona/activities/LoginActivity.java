package com.ihsinformatics.korona.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.MultiplePulse;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.databinding.ActivityLoginBinding;
import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.fragments.login.LoginContract;
import com.ihsinformatics.korona.model.FailureStatus;
import com.ihsinformatics.korona.model.geocode.GeocodeResult;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.util.List;

import javax.inject.Inject;

import static com.ihsinformatics.korona.fragments.login.LoginPresenterImpl.MY_PERMISSIONS_REQUEST_LOCATION;
import static com.ihsinformatics.korona.fragments.login.LoginPresenterImpl.REQUEST_LOCATION;

public class LoginActivity extends BaseActivity implements LoginContract.View, DialogInterface.OnClickListener, View.OnClickListener {

    public static final String FORM = "form";
    @Inject
    LoginContract.Presenter presenter;

    ActivityLoginBinding binding;
    private GeocodeResult geocodeResult;
    private ArrayAdapter<Location> stateAdapter;
    private FailureStatus failureStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        presenter.takeView(this);
        binding.refresh.setOnClickListener(this);
        presenter.syncLocations();
        if (presenter.getLastLocation() != null) {
            updateLocation(presenter.getLastLocation());

        } else {
            presenter.checkPermission(this);
        }
    }

    @Override
    public void showToast(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    public void onLoginButtonClicked(View view) {
        if (isValidDetails()) {
            presenter.fetchForm((Location) binding.state.spinner.getSelectedItem());
            binding.layoutLoading.setVisibility(View.VISIBLE);
            binding.layoutLocation.setVisibility(View.GONE);
            toggleRefresh(View.GONE, FailureStatus.NONE);
        }
    }

    private boolean isValidDetails() {
        boolean isAllValid = true;

        /*if (binding.country.spinner.getSelectedItem() == null) {
            isAllValid = false;
            showToast("Please select your country");
        }*/

        if (binding.state.spinner.getSelectedItem() == null) {
            isAllValid = false;
            showToast("Please select your State/Province");
        }

        if (!binding.agreed.isChecked()) {
            isAllValid = false;
            showToast(getString(R.string.error_term_condition));
        }


        return isAllValid;
    }

    @Override
    public void startMainActivity(QuizResponse response) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(FORM, new Gson().toJson(response));
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoading() {
        binding.loader.spinKit.setIndeterminateDrawable(new MultiplePulse());
        binding.loader.root.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.loader.root.setVisibility(View.GONE);
    }


    @Override
    public void showLocationReasonDialog() {
        AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
                .setTitle("Location Required")
                .setMessage("This app required location to show the details on the basis of your location")
                .setCancelable(false)
                .setIcon(R.drawable.ic_location)
                .setNeutralButton("OK", LoginActivity.this)
                .create();
        alertDialog.show();
    }

    @Override
    public void updateLocation(GeocodeResult geocodeResult) {
        this.geocodeResult = geocodeResult;

        if (stateAdapter != null) {
            Location state = presenter.getLocationFromName(geocodeResult.getRegion());
            selectSpinnerItemByValue(state);
        } else if (!presenter.getStates().isEmpty()) {
            setAdapter(presenter.getStates());
            Location state = presenter.getLocationFromName(geocodeResult.getRegion());
            selectSpinnerItemByValue(state);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.getUserLocation(this);
                } else {
                    showLocationReasonDialog();
                }
                return;
            }

        }
    }

    @Override
    public void toggleRefresh(int visibility, FailureStatus status) {
        failureStatus = status;
        if (FailureStatus.FETCHING_FORM.equals(failureStatus)) {
            binding.layoutLocation.setVisibility(View.VISIBLE);
        } else {
            binding.refresh.setVisibility(visibility);
        }
        binding.loadingText.setVisibility(visibility == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
        binding.loadingView.setVisibility(visibility == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void showLocationLayout() {
        binding.layoutLoading.setVisibility(View.GONE);
        binding.layoutLocation.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAdapter(List<Location> response) {

        stateAdapter = new ArrayAdapter<Location>(this, android.R.layout.simple_list_item_1, response);
        // binding.country.spinner.setAdapter(countryAdapter);
        binding.state.spinner.setAdapter(stateAdapter);

        if (geocodeResult != null) {
            Location state = presenter.getLocationFromName(geocodeResult.getRegion());
            selectSpinnerItemByValue(state);
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        presenter.requestPermission(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOCATION) {
            if (resultCode == RESULT_OK)
                presenter.fetchLocationDetails(this);
        }

    }

    @Override
    public void onClick(View view) {
        if (FailureStatus.FETCHING_LOCATION.equals(failureStatus))
            presenter.syncLocations();
        if (FailureStatus.FETCHING_FORM.equals(failureStatus))
            presenter.fetchForm((Location) binding.state.spinner.getSelectedItem());
        toggleRefresh(View.GONE, FailureStatus.NONE);
    }

    public void selectSpinnerItemByValue(Location value) {
        for (int position = 0; position < stateAdapter.getCount(); position++) {
            if (stateAdapter.getItem(position).getLocationId() == value.getLocationId()) {
                binding.state.spinner.setSelection(position);
                return;
            }
        }
    }


}
