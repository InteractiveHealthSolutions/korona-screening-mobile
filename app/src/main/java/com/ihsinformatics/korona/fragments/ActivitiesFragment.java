package com.ihsinformatics.korona.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ihsinformatics.korona.R;
import com.ihsinformatics.korona.adapter.ActivitiesAdapter;
import com.ihsinformatics.korona.adapter.AdapterListener;
import com.ihsinformatics.korona.db.Converters;
import com.ihsinformatics.korona.model.question.QuizResponse;

import java.util.List;

import static com.ihsinformatics.korona.activities.LoginActivity.FORM_TYPE;
import static com.ihsinformatics.korona.network.Endpoints.FORM_DATA;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ActivitiesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ActivitiesFragment extends Fragment implements AdapterListener.FormClickedListener, View.OnClickListener {

    private List<QuizResponse> formTypeResponses;
    private View view;

    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            String formType = arguments.getString(FORM_TYPE);
            formTypeResponses = Converters.toFormTypeList(formType);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         view = inflater.inflate(R.layout.fragment_activities, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        view.findViewById(R.id.partners).setOnClickListener(this);

        RecyclerView activities = view.findViewById(R.id.activities);


        ActivitiesAdapter activitiesAdapter = new ActivitiesAdapter(formTypeResponses,this);
        activities.setHasFixedSize(true);
        activities.setLayoutManager(new LinearLayoutManager(getActivity()));
        activities.setAdapter(activitiesAdapter);

    }

    @Override
    public void onFormClicked(QuizResponse activity) {
        Bundle bundle = new Bundle();
        bundle.putString(FORM_DATA ,new Gson().toJson(activity));
        Navigation.findNavController(view).navigate(R.id.action_activitiesFragment_to_formFragment4,bundle);
    }

    @Override
    public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_activitiesFragment_to_partnersFragment);
    }


}
