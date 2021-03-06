package com.ihsinformatics.korona.views;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.adapter.AdapterListener;
import com.ihsinformatics.korona.model.question.Questions;

import java.util.ArrayList;
import java.util.List;

public class EditTextWidget {

    private Context context;
    private Questions question;
    private AdapterListener.OptionClickedListener optionClickedListener;
    private String defaultValue;
    private int inputType;
    private int length;
    private boolean isMandatory;
    private boolean isSingleLine = true;
    private InputFilter inputFilter;
    private View view;


    public EditTextWidget(Context context, Questions question, int inputType, int length, boolean isMandatory) {
        this.context = context;
        this.question = question;
        this.inputType = inputType;
        this.length = length;
        this.isMandatory = isMandatory;
    }

    public EditTextWidget(Context context, Questions question, AdapterListener.OptionClickedListener optionClickedListener) {

        this.context = context;
        this.question = question;
        this.optionClickedListener = optionClickedListener;
    }


    public EditTextWidget singleLine(boolean isSingleLine) {
        this.isSingleLine = isSingleLine;
        return this;
    }

    public EditTextWidget defaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public EditTextWidget inputFilter(InputFilter filter) {
        this.inputFilter = filter;
        return this;
    }




    private InputFilter[] getInputFilters() {
        List<InputFilter> filterList = new ArrayList<>();
        filterList.add(new InputFilter.LengthFilter(length));
        if (inputFilter != null)
            filterList.add(inputFilter);

        int size = filterList.size();
        return filterList.toArray(new InputFilter[size]);
    }

    public View build() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.edit_text, null);
        TextInputEditText editText = (TextInputEditText) view.findViewById(R.id.edit_text);
        TextInputLayout hint = (TextInputLayout) view.findViewById(R.id.hint);
        hint.setHint("");
        editText.setInputType(inputType);
        InputFilter[] filters = getInputFilters();
        editText.setFilters(filters);
        editText.setText(defaultValue);
        editText.setMaxLines(isSingleLine ? 1 : 4);
        return view;
    }


}
