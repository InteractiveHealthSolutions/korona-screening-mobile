package com.ihsinformatics.korona.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.button.MaterialButton;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.adapter.AdapterListener;
import com.ihsinformatics.korona.databinding.WidgetQuizOptionBinding;

import com.ihsinformatics.korona.model.WidgetData;
import com.ihsinformatics.korona.model.question.Option;
import com.ihsinformatics.korona.model.question.Questions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class OptionWidget extends Widget {


    private Context context;
    private WidgetQuizOptionBinding binding;
    private Questions question;
    private AdapterListener.OptionClickedListener optionClickedListener;
    private boolean isAnswered = false;
    private MaterialButton biggestOption;
    private int maxHeight = 0;


    public OptionWidget(Context context, Questions question, AdapterListener.OptionClickedListener optionClickedListener) {
        this.context = context;
        this.question = question;
        this.optionClickedListener = optionClickedListener;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.widget_quiz_option, null, false);
        List<Option> options = question.getOptions();
        Collections.sort(options, new Comparator<Option>() {
            @Override
            public int compare(Option option, Option t1) {
                return option.getDescription().compareToIgnoreCase(t1.getDescription());
            }
        });

        Collections.reverse(options);

        for (Option option : options) {
            addButton(option);

        }


    }

    private void updateHeight(int maxHeight) {
        int margin = context.getResources().getDimensionPixelOffset(R.dimen.margin_small);

        for (int i = 0; i < binding.parent.getChildCount(); i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, maxHeight);
            params.setMargins(margin, 0, margin, 0);
            View view = binding.parent.getChildAt(i);
            view.setLayoutParams(params);
            view.requestLayout();
        }
    }

    private void addButton(Option option) {
        int padding = context.getResources().getDimensionPixelOffset(R.dimen._10sdp);
        int height = context.getResources().getDimensionPixelOffset(R.dimen._100sdp);
        int margin = context.getResources().getDimensionPixelOffset(R.dimen.margin_small);

      /*  MaterialButton button = new MaterialButton(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin, 0, margin, 0);
        button.setLayoutParams(params);

        button.setText(option.getDescription());
        button.setAllCaps(false);
        button.setGravity(Gravity.CENTER);
        button.setCornerRadius(context.getResources().getDimensionPixelOffset(R.dimen.margin_very_large));
        button.setBackgroundColor(context.getResources().getColor(R.color.white));
        button.setTextColor(context.getResources().getColor(R.color.colorAccent));
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        button.setTypeface(ResourcesCompat.getFont(context, R.font.poppins_bold), Typeface.BOLD);
        button.setPadding(padding, padding, padding, padding);
        button.setTag(option);
        button.setOnClickListener(new CustomClickListener());


       */
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MaterialButton button = (MaterialButton) inflater.inflate(R.layout.material_button, null);
        button.setText(option.getDescription());
        button.setTag(option);
        button.setOnClickListener(new CustomClickListener());
        binding.parent.addView(button);

    }

    @Override
    public View getView() {
        return binding.getRoot();
    }

    @Override
    public WidgetData getValue() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public boolean hasAttribute() {
        return false;
    }

    @Override
    public Widget hideView() {
        return null;
    }

    @Override
    public Widget showView() {
        return null;
    }

    @Override
    public Widget addHeader(String headerText) {
        return null;
    }

    @Override
    public Integer getAttributeTypeId() {
        return null;
    }

    @Override
    public boolean isViewOnly() {
        return false;
    }

    private class CustomClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (!isAnswered) {
                isAnswered = true;
                MaterialButton radioButton = (MaterialButton) view;
                Option option = (Option) radioButton.getTag();
                optionClickedListener.onOptionClicked(question, option);
            }
        }
    }
}
