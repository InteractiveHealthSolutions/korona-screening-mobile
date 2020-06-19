package com.ihsinformatics.korona.fragments.form;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.activities.MentalHealthFlyer;
import com.ihsinformatics.korona.activities.ResultActivity;
import com.ihsinformatics.korona.adapter.AdapterListener;
import com.ihsinformatics.korona.adapter.FormAdapter;
import com.ihsinformatics.korona.common.Utils;
import com.ihsinformatics.korona.databinding.LayoutFormBinding;
import com.ihsinformatics.korona.model.FormAnswer;
import com.ihsinformatics.korona.model.question.Location;
import com.ihsinformatics.korona.model.question.Option;
import com.ihsinformatics.korona.model.question.Questions;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.util.List;

import javax.inject.Inject;

import lib.kingja.switchbutton.SwitchMultiButton;

import static android.text.TextUtils.isEmpty;
import static com.ihsinformatics.korona.network.Endpoints.FORM_DATA;

public class FormFragment extends Fragment implements FormContract.View, AdapterListener.OptionClickedListener, DialogInterface.OnClickListener, View.OnClickListener, SwitchMultiButton.OnSwitchListener {


    public static final int MENTAL_HEALTH_ACTIVITY = 12;
    LayoutFormBinding binding;

    @Inject
    FormContract.Presenter presenter;


    private AlertDialog countriesDialog;
    private List<Questions> questions;
    private String gender;

    QuizResponse formTypeResponse;
    private String formData;


    public FormFragment() {
        // Required empty public constructor
    }

   /* // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(QuizResponse formResponse) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(FORM_DATA, new Gson().toJson(formResponse));

        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            formData = getArguments().getString(FORM_DATA);
            formTypeResponse = new Gson().fromJson(formData, QuizResponse.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_form, container, false);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        presenter.takeView(this);
        init();
        return binding.getRoot();
    }

    private void init() {

        questions = presenter.getQuestions(formData);
        if (formTypeResponse.getFormGroup().getDefinitionId() == MENTAL_HEALTH_ACTIVITY) {
            binding.genderDetails.root.setVisibility(View.GONE);
            binding.quizPager.setVisibility(View.VISIBLE);
            binding.quizPager.requestFocus();
            Utils.hideKeyboardFrom(getActivity(), binding.quizPager);
        } else {
            showActivityDialog();
        }
        binding.genderDetails.country.setText(presenter.getLocation().getCountry());
        binding.genderDetails.state.setText(presenter.getLocation().getLocationName());
        binding.genderDetails.next.setOnClickListener(this);
        binding.genderDetails.gender.setOnSwitchListener(this);

        binding.quizPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        setAdapter();
        //moveAdapterToPosition(presenter.getFirstQuestionPosition());

    }

    private void showActivityDialog() {
        String description = presenter.getActivityDescription();

        AlertDialog alertDialog = new MaterialAlertDialogBuilder(getActivity())
                .setTitle("")
                .setMessage(description)
                .setCancelable(true)
                .setIcon(R.drawable.ic_info)
                .setPositiveButton(getStringResource(R.string.ok), this)
                .create();

        alertDialog.show();
    }


    private void setAdapter() {

        FormAdapter formAdapter = new FormAdapter(questions, presenter.getLocation(), this);
        binding.quizPager.setUserInputEnabled(false);
        binding.quizPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.quizPager.setAdapter(formAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.countries));
        countriesDialog = new MaterialAlertDialogBuilder(getActivity())
                .setAdapter(adapter, null)
                .setTitle(getStringResource(R.string.high_burden_countries))
                .setCancelable(false)
                .setIcon(R.drawable.ic_plane)
                .setPositiveButton(getStringResource(R.string.ok), this)
                .create();


    }

    @Override
    public void onOptionClicked(final Questions question, final Option option) {

        if (question.getCorrectOptionId().equals(option.getElementId())) {
            presenter.updateScore(question.getQuestionScore());
        }

        presenter.addAnswer(new FormAnswer(question.getUuid(), option.getShortName()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int position = binding.quizPager.getCurrentItem();

                if (question.getNextQuestionCriteriaRegex() != null
                        && option.getDescription().matches(question.getNextQuestionCriteriaRegex())
                        && question.getNextQuestion() != null) {

                    position = position + 1;
                    moveAdapterToPosition(position);

                } else {
                    presenter.sendResult();
                }
            }
        }, 800);

    }

    @Override
    public void onInfoClicked() {
        countriesDialog.show();
    }


    @Override
    public void showResult(String result, Location location) {
        Intent intent;
        if (formTypeResponse.getFormGroup().getDefinitionId() == MENTAL_HEALTH_ACTIVITY) {
            intent = new Intent(getActivity(), MentalHealthFlyer.class);
        } else {
            intent = new Intent(getActivity(), ResultActivity.class);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("result", result);
        intent.putExtra("location", location.getCountry());
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public String getStringResource(int resId) {
        return getResources().getString(resId);
    }

    @Override
    public void moveAdapterToPosition(int arrayPosition) {
        binding.quizPager.setCurrentItem(arrayPosition);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (countriesDialog != null && countriesDialog.isShowing()) {
            countriesDialog.dismiss();
        }
    }


    @Override
    public void onClick(View view) {
        if (view.equals(binding.genderDetails.next)) {
            if (isValid()) {
                presenter.addAnswer(new FormAnswer("age", binding.genderDetails.age.getText().toString()));
                presenter.addAnswer(new FormAnswer("gender", gender));
                binding.genderDetails.root.setVisibility(View.GONE);
                binding.quizPager.setVisibility(View.VISIBLE);
                binding.quizPager.requestFocus();
                Utils.hideKeyboardFrom(getActivity(), binding.quizPager);
            }
        }
    }

    private boolean isValid() {
        boolean isValid = true;

        if (isEmpty(binding.genderDetails.age.getText().toString())) {
            isValid = false;
            showToast("Please enter your age");
        } else {
            Integer age = Integer.valueOf(binding.genderDetails.age.getText().toString());
            if (age < 1 || age > 199) {
                isValid = false;
                showToast("Age should be between 1-199");
            }
        }

        if (binding.genderDetails.gender.getSelectedTab() < 0) {
            isValid = false;
            showToast("Please select your gender");
        }


        return isValid;

    }

    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwitch(int position, String tabText) {
        gender = tabText;
    }


}
