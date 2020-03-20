package com.ihsinformatics.covid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.button.MaterialButton;
import com.ihsinformatics.covid.R;
import com.ihsinformatics.covid.model.Question;
import com.ihsinformatics.covid.views.OptionWidget;

import java.util.List;

public class FormAdapter extends RecyclerView.Adapter<FormAdapter.QuizViewHolder> {

    List<Question> questions;
    Context context;
    AdapterListener.OptionClickedListener optionClickedListener;

    public FormAdapter(List<Question> questions, AdapterListener.OptionClickedListener optionClickedListener) {
        this.questions = questions;
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
        Question question = questions.get(position);
        holder.question.setText(question.getQuestion());
        holder.section.setText(question.getSectionName());
        holder.icon.setImageResource(question.getIcon());
        holder.questionNo.setText(context.getString(R.string.question) + " " + (position + 1) + "/" + questions.size());
        if (question.isCountriesAvailable()) {
            holder.countries.setVisibility(View.VISIBLE);
            holder.countries.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    optionClickedListener.onInfoClicked();
                }
            });

        } else {
            holder.countries.setVisibility(View.GONE);
        }

        if (holder.quizLayout.getChildCount() == 0) {
            OptionWidget quizOptionWidget = new OptionWidget(context, question, optionClickedListener);
            holder.quizLayout.addView(quizOptionWidget.getView());
        }
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {

        LinearLayout quizLayout;
        TextView question;
        TextView questionNo;
        TextView section;
        ImageView icon;
        MaterialButton countries;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            quizLayout = itemView.findViewById(R.id.quiz_layout);
            question = itemView.findViewById(R.id.question);
            questionNo = itemView.findViewById(R.id.question_no);
            section = itemView.findViewById(R.id.section);
            icon = itemView.findViewById(R.id.icon);
            countries = itemView.findViewById(R.id.countries);
        }
    }
}
