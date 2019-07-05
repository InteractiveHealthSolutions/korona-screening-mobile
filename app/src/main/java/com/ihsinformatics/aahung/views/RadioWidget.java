package com.ihsinformatics.aahung.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ihsinformatics.aahung.R;

import java.util.List;

import lib.kingja.switchbutton.SwitchMultiButton;

public class RadioWidget implements SwitchMultiButton.OnSwitchListener {

    private Context context;
    private String question;
    private SwitchMultiButton switchMultiButton;
    private String selectedText;
    private View view;

    public RadioWidget(Context context, String question) {
        this.context = context;
        this.question = question;
        init();
    }

    public RadioWidget addButtons(String... widgetTexts) {
        switchMultiButton.setText(widgetTexts);
        switchMultiButton.setOnSwitchListener(this);
        return this;
    }


    public View build() {
        return view;
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.radio_widget, null);
        switchMultiButton = view.findViewById(R.id.radio);
        ((TextView) view.findViewById(R.id.title)).setText(question);
    }


    @Override
    public void onSwitch(int position, String tabText) {
        selectedText = tabText;
    }
}
