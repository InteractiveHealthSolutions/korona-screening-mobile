package com.ihsinformatics.korona.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.ihsinformatics.korona.App;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.adapter.AdapterListener;
import com.ihsinformatics.korona.adapter.FormAdapter;
import com.ihsinformatics.korona.databinding.LayoutFormBinding;
import com.ihsinformatics.korona.fragments.form.FormContract;
import com.ihsinformatics.korona.model.FormAnswer;
import com.ihsinformatics.korona.model.Option;
import com.ihsinformatics.korona.model.Question;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements FormContract.View, AdapterListener.OptionClickedListener, DialogInterface.OnClickListener {

    LayoutFormBinding binding;

    @Inject
    FormContract.Presenter presenter;

    List<Question> questions;
    private AlertDialog countriesDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.layout_form);

        ((App) getApplication()).getComponent().inject(this);
        presenter.takeView(this);
        questions = presenter.getQuestions();

        setAdapter();

    }


    private void setAdapter() {

        FormAdapter formAdapter = new FormAdapter(questions, this);
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
    public void onOptionClicked(Question question, Option option) {
        presenter.updateScore(new FormAnswer(question.getShortName(), option), option.getScore(), question.getSection());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int position = binding.quizPager.getCurrentItem();
                if (position < (questions.size() - 1)) {
                    position = position + 1;
                    binding.quizPager.setCurrentItem(position);
                } else {
                    presenter.sendResult();
                }
            }
        }, 1000);

    }

    @Override
    public void onInfoClicked() {
        countriesDialog.show();
    }


    @Override
    public void showResult(Integer totalScore) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("score", totalScore);
        startActivity(intent);
        finish();
    }

    @Override
    public String getStringResource(int resId) {
        return getResources().getString(resId);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (countriesDialog != null && countriesDialog.isShowing()) {
            countriesDialog.dismiss();
        }
    }

}
