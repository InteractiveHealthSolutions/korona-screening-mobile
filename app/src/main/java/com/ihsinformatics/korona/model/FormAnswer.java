package com.ihsinformatics.korona.model;

import com.ihsinformatics.korona.model.question.Option;

public class FormAnswer {
    String question;
    String answer;

    public FormAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestionId() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
