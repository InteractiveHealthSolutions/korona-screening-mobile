package com.ihsinformatics.korona.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.databinding.DataBindingUtil;

import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.databinding.SpinnerWidgetBinding;

import java.util.List;

public class SpinnerWidget {

    private final Context context;
    private final String question;
    private final List<String> items;
    private SpinnerWidgetBinding binding;

    public SpinnerWidget(Context context, String question, List<String> items) {
        this.context = context;
        this.question = question;
        this.items = items;
        init();

    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.spinner_widget, null, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, items);
        binding.spinner.setAdapter(adapter);
        //binding.title.setText(question);
    }


    public View build() {
        return binding.getRoot();
    }

}
