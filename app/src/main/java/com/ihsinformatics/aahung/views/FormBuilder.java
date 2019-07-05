package com.ihsinformatics.aahung.views;

import android.content.Context;
import android.text.InputType;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class FormBuilder {


    private Context context;
    private LinearLayout baseLayout;


    public FormBuilder(Context context, LinearLayout baseLayout) {
        this.baseLayout = baseLayout;
        this.context = context;
    }

    public LinearLayout build() {

        EditTextWidget customEditText = new EditTextWidget(context, "This is the test question", InputType.TYPE_CLASS_TEXT, 100, false);
        RadioWidget radioWidget = new RadioWidget(context, "Gender").addButtons("male", "female", "others");
        SpinnerWidget widget = new SpinnerWidget(context, "List of items", Arrays.asList(new String[]{"abba", "tabba", "kabba"}));

        baseLayout.addView(customEditText.build());
        baseLayout.addView(customEditText.build());
        baseLayout.addView(radioWidget.build());
        baseLayout.addView(widget.build());

        return baseLayout;
    }

    public FormBuilder setLayout(LinearLayout baselayout) {
        this.baseLayout = baselayout;
        return this;
    }
}
