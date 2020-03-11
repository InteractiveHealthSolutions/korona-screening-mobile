package com.ihsinformatics.covid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ihsinformatics.covid.App;
import com.ihsinformatics.covid.R;
import com.ihsinformatics.covid.fragments.form.FormContract;
import com.ihsinformatics.covid.views.FormBuilder;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements FormContract.View {

    @Inject
    FormContract.Presenter presenter;

    @Inject
    FormBuilder formBuilder;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_form);
        ((App) getApplication()).getComponent().inject(this);
        presenter.takeView(this);
        setupForm((LinearLayout) findViewById(R.id.baselayout));
    }

    private void setupForm(LinearLayout baselayout) {
        FormBuilder formBuilder = new FormBuilder(MainActivity.this,baselayout);
        formBuilder.build();
    }

}
