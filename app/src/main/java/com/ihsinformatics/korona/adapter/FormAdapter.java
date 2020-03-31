package com.ihsinformatics.korona.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.button.MaterialButton;
import com.ihsinformatics.korona.R;

import com.ihsinformatics.korona.model.question.Location;
import com.ihsinformatics.korona.model.question.Questions;
import com.ihsinformatics.korona.views.EditTextWidget;
import com.ihsinformatics.korona.views.OptionWidget;

import java.util.List;

import static com.ihsinformatics.korona.model.QuestionView.RADIO_GROUP;
import static com.ihsinformatics.korona.model.QuestionView.TEXT_BOX;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.QuizViewHolder> {

    List<Questions> questions;
    Context context;
    private Location location;
    AdapterListener.OptionClickedListener optionClickedListener;

    public FormAdapter(List<Questions> questions, Location location, AdapterListener.OptionClickedListener optionClickedListener) {
        this.questions = questions;
        this.location = location;
        this.optionClickedListener = optionClickedListener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz_questions, parent, false);
        context = parent.getContext();
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Questions question = questions.get(position);
        holder.question.setText(question.getQuestion().getDescription());
        holder.state.setText(location.getLocationName());
        holder.country.setText(location.getCountry());

        if (holder.quizLayout.getChildCount() == 0) {
            if (RADIO_GROUP.name().equals(question.getQuestionView())) {
                OptionWidget quizOptionWidget = new OptionWidget(context, question, optionClickedListener);
                holder.quizLayout.addView(quizOptionWidget.getView());
            } else if (TEXT_BOX.name().equals(question.getQuestionView())) {
                EditTextWidget editTextWidget = new EditTextWidget(context, question, optionClickedListener);
                holder.quizLayout.addView(editTextWidget.build());
            }
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {

        final TextView country;
        LinearLayout quizLayout;
        TextView question;
        TextView questionNo;
        TextView state;

        MaterialButton countries;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            quizLayout = itemView.findViewById(R.id.quiz_layout);
            question = itemView.findViewById(R.id.question);
            questionNo = itemView.findViewById(R.id.question_no);
            state = itemView.findViewById(R.id.state);
            country = itemView.findViewById(R.id.country);
            countries = itemView.findViewById(R.id.countries);
        }
    }
}
