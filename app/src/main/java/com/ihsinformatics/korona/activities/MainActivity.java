package com.ihsinformatics.korona.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.adapter.AdapterListener;
import com.ihsinformatics.korona.adapter.FormAdapter;
import com.ihsinformatics.korona.common.Utils;
import com.ihsinformatics.korona.databinding.LayoutFormBinding;
import com.ihsinformatics.korona.fragments.form.FormContract;
import com.ihsinformatics.korona.model.FormAnswer;

import com.ihsinformatics.korona.model.question.Option;
import com.ihsinformatics.korona.model.question.Questions;

import java.util.List;

import javax.inject.Inject;

import lib.kingja.switchbutton.SwitchMultiButton;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends BaseActivity implements FormContract.View, AdapterListener.OptionClickedListener, DialogInterface.OnClickListener, View.OnClickListener, SwitchMultiButton.OnSwitchListener {

    LayoutFormBinding binding;

    @Inject
    FormContract.Presenter presenter;


    private AlertDialog countriesDialog;
    private List<Questions> questions;
    private String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.layout_form);
        ((App) getApplication()).getComponent().inject(this);
        presenter.takeView(this);
        init();


    }

    private void init() {
        questions = presenter.getQuestions(getIntent());
        binding.genderDetails.country.setText(presenter.getLocation().getCountry());
        binding.genderDetails.state.setText(presenter.getLocation().getLocationName());
        binding.genderDetails.next.setOnClickListener(this);
        binding.genderDetails.gender.setOnSwitchListener(this);

        setAdapter();
        //moveAdapterToPosition(presenter.getFirstQuestionPosition());
        showActivityDialog();
    }

    private void showActivityDialog() {
        String description = presenter.getActivityDescription();

        AlertDialog alertDialog = new MaterialAlertDialogBuilder(this)
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
        binding.quizPager.setOffscreenPageLimit(questions.size());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, getResources().getStringArray(R.array.countries));
        countriesDialog = new MaterialAlertDialogBuilder(this)
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
    public void showResult(String result) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("result", result);
        startActivity(intent);
        finish();
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
                Utils.hideKeyboardFrom(this, binding.quizPager);
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSwitch(int position, String tabText) {
        gender = tabText;
    }
}
