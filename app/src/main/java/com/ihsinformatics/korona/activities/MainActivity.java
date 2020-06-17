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

import com.ihsinformatics.korona.model.question.Location;
import com.ihsinformatics.korona.model.question.Option;
import com.ihsinformatics.korona.model.question.Questions;

import java.util.List;

import javax.inject.Inject;

import lib.kingja.switchbutton.SwitchMultiButton;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends BaseActivity{
    LayoutFormBinding binding;

    @Inject
    FormContract.Presenter presenter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.layout_form);
        ((App) getApplication()).getComponent().inject(this);




    }


}
