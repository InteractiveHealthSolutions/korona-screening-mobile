package com.ihsinformatics.covid.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.button.MaterialButton;
import com.ihsinformatics.covid.R;
import com.ihsinformatics.covid.adapter.AdapterListener;
import com.ihsinformatics.covid.databinding.WidgetQuizOptionBinding;
import com.ihsinformatics.covid.model.Option;
import com.ihsinformatics.covid.model.Question;
import com.ihsinformatics.covid.model.WidgetData;


public class OptionWidget extends Widget {


    private Context context;
    private WidgetQuizOptionBinding binding;
    private Question question;
    private AdapterListener.OptionClickedListener optionClickedListener;

    public OptionWidget(Context context, Question question, AdapterListener.OptionClickedListener optionClickedListener) {
        this.context = context;
        this.question = question;
        this.optionClickedListener = optionClickedListener;
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.widget_quiz_option, null, false);

        for (Option option : question.getOptions()) {
            addButton(option);
        }
    }

    private void addButton(Option option) {
        int padding = context.getResources().getDimensionPixelOffset(R.dimen._10sdp);
        int margin = context.getResources().getDimensionPixelOffset(R.dimen.margin_small);

        MaterialButton radioButton = new MaterialButton(context);
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(margin, 0, margin, 0);
        radioButton.setLayoutParams(layoutParams);
        radioButton.setText(context.getResources().getString(option.getOptionValue().getTextResId()));
        radioButton.setAllCaps(false);
        radioButton.setGravity(Gravity.CENTER);
        radioButton.setCornerRadius(context.getResources().getDimensionPixelOffset(R.dimen.margin_very_large));
        radioButton.setBackgroundColor(context.getResources().getColor(R.color.white));
        radioButton.setTextColor(context.getResources().getColor(R.color.colorAccent));
        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        radioButton.setTypeface(ResourcesCompat.getFont(context, R.font.poppins_bold), Typeface.BOLD);

        radioButton.setPadding(padding, padding, padding, padding);
        radioButton.setTag(option);
        radioButton.setOnClickListener(new CustomClickListener());
        binding.parent.addView(radioButton);
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

            MaterialButton radioButton = (MaterialButton) view;
            Option option = (Option) radioButton.getTag();
            optionClickedListener.onOptionClicked(question, option);

        }
    }
}
