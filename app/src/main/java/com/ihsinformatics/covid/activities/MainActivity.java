package com.ihsinformatics.covid.activities;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import com.ihsinformatics.covid.App;
import com.ihsinformatics.covid.R;
import com.ihsinformatics.covid.adapter.AdapterListener;
import com.ihsinformatics.covid.adapter.FormAdapter;
import com.ihsinformatics.covid.databinding.LayoutFormBinding;
import com.ihsinformatics.covid.fragments.form.FormContract;
import com.ihsinformatics.covid.model.FormAnswer;
import com.ihsinformatics.covid.model.Option;
import com.ihsinformatics.covid.model.Question;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements FormContract.View, AdapterListener.OptionClickedListener {

    LayoutFormBinding binding;

    @Inject
    FormContract.Presenter presenter;

    List<Question> questions;


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

    }

    @Override
    public void onOptionClicked(Question question, Option option) {
        presenter.updateScore(new FormAnswer(question.getShortName(), option), option.getScore(), question.getSection());
        int position = binding.quizPager.getCurrentItem();
        if (position < (questions.size() - 1)) {
            position = position + 1;
            binding.quizPager.setCurrentItem(position);
        } else {
            presenter.sendResult();
            //Toast.makeText(MainActivity.this, "You have Corona Virus...", Toast.LENGTH_SHORT).show();
        }
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
}
