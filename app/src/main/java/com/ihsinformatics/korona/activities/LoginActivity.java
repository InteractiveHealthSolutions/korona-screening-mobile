package com.ihsinformatics.korona.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.MultiplePulse;
import com.google.gson.Gson;
import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.databinding.ActivityLoginBinding;
import com.ihsinformatics.korona.db.Converters;
import com.ihsinformatics.korona.db.entities.Location;
import com.ihsinformatics.korona.fragments.ConsentFragment;
import com.ihsinformatics.korona.fragments.location.automatic.DetectLocationFragment;
import com.ihsinformatics.korona.fragments.location.manual.ManualLocationFragment;
import com.ihsinformatics.korona.fragments.login.LoginContract;
import com.ihsinformatics.korona.model.FailureStatus;
import com.ihsinformatics.korona.model.geocode.GeocodeResult;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.util.List;

import javax.inject.Inject;

import static com.ihsinformatics.korona.fragments.login.LoginPresenterImpl.MY_PERMISSIONS_REQUEST_LOCATION;
import static com.ihsinformatics.korona.fragments.login.LoginPresenterImpl.REQUEST_LOCATION;

public class LoginActivity extends FragmentActivity implements LoginContract.View, DialogInterface.OnClickListener, View.OnClickListener {

    public static final String FORM = "form";
    public  static final String FORM_TYPE = "formtYpe";
    @Inject
    LoginContract.Presenter presenter;

    ActivityLoginBinding binding;
    private GeocodeResult geocodeResult;
    private ArrayAdapter<Location> stateAdapter;
    private FailureStatus failureStatus;
    ScreenSlidePagerAdapter pagerAdapter;
    private Location selectedLocation;
    DetectLocationFragment detectLocationFragment;
    ManualLocationFragment manualLocationFragment;
    ConsentFragment consentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        presenter.takeView(this);

        init();


    }

    private void init() {
        detectLocationFragment = new DetectLocationFragment();
        manualLocationFragment = new ManualLocationFragment();
        consentFragment = new ConsentFragment();


        pagerAdapter = new ScreenSlidePagerAdapter(this);
        binding.pager.setUserInputEnabled(false);
        binding.pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.pager.setAdapter(pagerAdapter);

        binding.ihs.setClickable(true);
        binding.ihs.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='http://www.ihsinformatics.com/'> IHS </a>";
        binding.ihs.setText(Html.fromHtml(text));
    }

    @Override
    public void showToast(String Message) {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void startMainActivity(QuizResponse response) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtra(FORM, new Gson().toJson(response));
        startActivity(intent);
        finish();
    }

    @Override
    public void startTabbedActivity(List<QuizResponse> response) {
        Intent intent = new Intent(LoginActivity.this, TabbedActivity.class);
        intent.putExtra(FORM_TYPE, Converters.fromFormTypeList(response));
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

    }

    @Override
    public void updateLocation(GeocodeResult geocodeResult) {
        this.geocodeResult = geocodeResult;

     /*   if (stateAdapter != null) {
            Location state = presenter.getLocationFromName(geocodeResult.getRegion());
            selectSpinnerItemByValue(state);
        } else if (!presenter.getStates().isEmpty()) {
            setAdapter(presenter.getStates());
            Location state = presenter.getLocationFromName(geocodeResult.getRegion());
            selectSpinnerItemByValue(state);
        }*/
    }

    @Override
    public void toggleRefresh(int visibility, FailureStatus status) {
        if (FailureStatus.FETCHING_FORM.equals(status)) {
            consentFragment.removeLoader();
        }
    }

    @Override
    public void showLocationLayout() {

    }

    @Override
    public void setAdapter(List<Location> response) {

    }

    @Override
    public void showNoFormFound() {
        consentFragment.showNoFormLayout();
        consentFragment.removeConsentLayout();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    detectLocationFragment.getUserLocation();
                } else {
                    detectLocationFragment.showLocationError();
                    showLocationReasonDialog();
                }
                return;
            }

        }
    }

    /*  @Override
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
  */
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOCATION) {
            if (resultCode == RESULT_OK)
                detectLocationFragment.fetchLocation();
            else
                detectLocationFragment.showLocationError();
        }

    }

    @Override
    public void onClick(View view) {

    }

    public void changePage(int position) {
        //int position = binding.pager.getCurrentItem();
        binding.pager.setCurrentItem(position);
        pagerAdapter.notifyDataSetChanged();
    }

    public void setSelectedLocation(Location selectedLocation) {
        this.selectedLocation = selectedLocation;
    }

    public void fetchForm() {
        //presenter.fetchForm(selectedLocation);
        presenter.fetchFormTypes(selectedLocation);
    }

    public void onPartnersButtonClicked(View view) {
        binding.mainLayout.setVisibility(View.GONE);
    }

    public void hidePartners() {
        binding.mainLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onBackPressed() {
        if(binding.mainLayout.getVisibility()  == View.VISIBLE) {
            super.onBackPressed();
        }else {
            hidePartners();
        }
    }

    /*    @Override
            public void onClick(View view) {
                if (view.equals(binding.refresh)) {
                    if (FailureStatus.FETCHING_LOCATION.equals(failureStatus))
                        presenter.syncLocations();
                    if (FailureStatus.FETCHING_FORM.equals(failureStatus))
                        presenter.fetchForm((Location) binding.state.spinner.getSelectedItem());
                    toggleRefresh(View.GONE, FailureStatus.NONE);
                } else if (view.equals(binding.requestNow)) {
                    presenter.submitFormRequestForm(geocodeResult);
                }
            }

            public void selectSpinnerItemByValue(Location value) {
                for (int position = 0; position < stateAdapter.getCount(); position++) {
                    if (value != null) {
                        if (stateAdapter.getItem(position).getLocationId() == value.getLocationId()) {
                            binding.state.spinner.setSelection(position);
                            return;
                        }
                    }
                }
            }*/
    private class ScreenSlidePagerAdapter extends FragmentStateAdapter {


        public ScreenSlidePagerAdapter(FragmentActivity activity) {
            super(activity);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = detectLocationFragment;
                    break;
                case 1:
                    fragment = manualLocationFragment;
                    break;
                case 2:
                    fragment = consentFragment;
                    break;
            }
            return fragment;
        }


        @Override
        public int getItemCount() {
            return 3;
        }
    }

}
