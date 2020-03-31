package com.ihsinformatics.korona.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.databinding.FragmentRequestBinding;
import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.model.raw.CountriesResponse;
import com.ihsinformatics.korona.model.raw.Country;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RequestFragment extends DialogFragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private static final String COUNTRY_KEY = "countries";


    private RequestDialogListener listener;
    FragmentRequestBinding binding;

    private String countriesJSON;
    private ArrayAdapter<String> stateAdapter;

    public RequestFragment() {
        // Required empty public constructor
    }

    public static RequestFragment newInstance(String countries) {
        RequestFragment fragment = new RequestFragment();
        Bundle args = new Bundle();
        args.putString(COUNTRY_KEY, countries);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            countriesJSON = getArguments().getString(COUNTRY_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_request, container, false);
        initAdapter();
        return binding.getRoot();
    }


    private void initAdapter() {
        binding.countries.title.setText("Select Country");
        binding.states.title.setText("Select State/Province");
        binding.submitButton.setText("Request Now");
        binding.submitButton.setOnClickListener(this);

        CountriesResponse countriesResponse = new Gson().fromJson(countriesJSON, CountriesResponse.class);

        //stateAdapter = new ArrayAdapter<Country>(getActivity(), android.R.layout.simple_list_item_1, );
        ArrayAdapter countryAdapter = new ArrayAdapter<Country>(getActivity(), android.R.layout.simple_list_item_1, countriesResponse.getCountries());
        binding.countries.spinner.setAdapter(countryAdapter);
        binding.countries.spinner.setOnItemSelectedListener(this);
        // binding.states.spinner.setAdapter(stateAdapter);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Fragment fragment = getTargetFragment();
        try {
            if (fragment != null) {
                listener = (RequestDialogListener) fragment;
            } else {
                listener = (RequestDialogListener) context;
            }
        } catch (Exception e) {
            throw new RuntimeException(context.toString() + "must implement");
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Country country = (Country) adapterView.getItemAtPosition(i);
        if (country != null) {
            stateAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, country.getStates());
            binding.states.spinner.setAdapter(stateAdapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
// stub
    }

    @Override
    public void onClick(View view) {
        if(validate())
        {
            listener.onRequestCompleted(((Country)binding.countries.spinner.getSelectedItem()).getCountry(),binding.states.spinner.getSelectedItem().toString());
            dismiss();
        }
    }

    private boolean validate() {
        boolean isValid = true;

        if (binding.countries.spinner.getSelectedItem() == null) {
            isValid = false;
        }

        if (binding.states.spinner.getSelectedItem() == null) {
            isValid = false;
        }

        return isValid;
    }


    public interface RequestDialogListener {
        public void onRequestCompleted(String country, String state);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
