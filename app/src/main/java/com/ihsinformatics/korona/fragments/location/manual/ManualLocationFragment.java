package com.ihsinformatics.korona.fragments.location.manual;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.activities.LoginActivity;
import com.ihsinformatics.korona.databinding.LayoutLocationBinding;
import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.fragments.RequestFragment;
import com.ihsinformatics.korona.model.FailureStatus;
import com.ihsinformatics.korona.model.geocode.GeocodeResult;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManualLocationFragment extends Fragment implements View.OnClickListener, ManualLocationContract.View, AdapterView.OnItemSelectedListener, RequestFragment.RequestDialogListener {

    private ArrayAdapter<Location> stateAdapter;
    private GeocodeResult geocodeResult;

    public ManualLocationFragment() {
        // Required empty public constructor
    }

    @Inject
    ManualLocationContract.Presenter presenter;

    LayoutLocationBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_location, container, false);
        presenter.takeView(this);
        init();
        return binding.getRoot();

    }

    private void init() {
        presenter.syncLocations();
        presenter.createLocationsJson(getResources().openRawResource(R.raw.countries));
        binding.loading.refresh.setOnClickListener(this);
        binding.requestNow.setOnClickListener(this);
        binding.next.setOnClickListener(this);
        binding.state.title.setText("State/Province");
        binding.country.title.setText("Country");
    }

    @Override
    public void onClick(View view) {
        if (view.equals(binding.requestNow)) {
            if (!presenter.isLocationRequestSubmitted()) {
                if (presenter.getLastLocation() != null)
                    presenter.submitFormRequestForm(presenter.getLastLocation().getCountry(), presenter.getLastLocation().getRegion());
                else
                    showRequestDialog();
            } else {
                showToast("Your request has already submitted");
            }
        } else if (view.equals(binding.loading.refresh)) {
            presenter.syncLocations();
            toggleRefresh(View.GONE, FailureStatus.NONE);
            binding.loading.layoutLoading.setVisibility(View.VISIBLE);
            binding.layoutManualLocation.setVisibility(View.GONE);

        } else if (view.equals(binding.next)) {
            if (isValidDetails()) {
                presenter.fetchForm((Location) binding.state.spinner.getSelectedItem());
                //  binding.loading.layoutLoading.setVisibility(View.VISIBLE);
                binding.layoutManualLocation.setVisibility(View.GONE);
                toggleRefresh(View.GONE, FailureStatus.NONE);
                ((LoginActivity) getActivity()).changePage(2);
                ((LoginActivity) getActivity()).setSelectedLocation((Location) binding.state.spinner.getSelectedItem());

            }
        }

    }

    private void showRequestDialog() {
        String json = presenter.getCountriesJSON();
        if (json != null) {
            RequestFragment requestFragment = RequestFragment.newInstance(json);
            requestFragment.setTargetFragment(this, 24);
            if (getFragmentManager() != null)
                requestFragment.show(getFragmentManager(), "requestFragment");
        }
    }


    private boolean isValidDetails() {
        boolean isAllValid = true;

        if (binding.country.spinner.getSelectedItem() == null) {
            isAllValid = false;
            showToast("Please select your country");
        }

        if (binding.state.spinner.getSelectedItem() == null) {
            isAllValid = false;
            showToast("Please select your State/Province");
        }

       /* if (!binding.agreed.isChecked()) {
            isAllValid = false;
            showToast(getString(R.string.error_term_condition));
        }*/

        return isAllValid;
    }

    @Override
    public void showToast(String Message) {
        if (getActivity() != null)
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

    }

    @Override
    public void showLocationReasonDialog() {

    }

    @Override
    public void updateLocation(GeocodeResult geocodeResult) {

    }

    @Override
    public void toggleRefresh(int visibility, FailureStatus status) {

        binding.loading.layoutLoading.setVisibility(View.VISIBLE);
        binding.layoutManualLocation.setVisibility(View.GONE);
        binding.loading.refresh.setVisibility(visibility);
        binding.loading.loadingText.setVisibility(visibility == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
        binding.loading.loadingView.setVisibility(visibility == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void showLocationLayout() {
        binding.layoutManualLocation.setVisibility(View.VISIBLE);
        binding.loading.layoutLoading.setVisibility(View.GONE);

    }

    @Override
    public void setAdapter(List<Location> countries, List<Location> states) {
        stateAdapter = new ArrayAdapter<Location>(getActivity(), android.R.layout.simple_list_item_1, states);
        ArrayAdapter countryAdapter = new ArrayAdapter<Location>(getActivity(), android.R.layout.simple_list_item_1, countries);
        binding.country.spinner.setAdapter(countryAdapter);
        binding.country.spinner.setOnItemSelectedListener(this);
        binding.state.spinner.setAdapter(stateAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Location location = (Location) adapterView.getItemAtPosition(i);
        if (location != null) {
            List<Location> states = presenter.getLocationsByParent(location.getLocationId());
            stateAdapter = new ArrayAdapter<Location>(getActivity(), android.R.layout.simple_list_item_1, states);
            binding.state.spinner.setAdapter(stateAdapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //STUB
    }

    @Override
    public void onRequestCompleted(String country, String state) {
        presenter.submitFormRequestForm(country, state);

    }
}
