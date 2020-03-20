package com.ihsinformatics.korona.model;

public class FormAnswer {
    String question;
    Option answer;

    public FormAnswer(String question, Option answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestionId() {
        return question;
    }

    public Option getAnswer() {
        return answer;
    }
}
