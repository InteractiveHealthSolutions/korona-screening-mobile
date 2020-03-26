
package com.ihsinformatics.korona.model.question;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questions {

    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("isRetired")
    @Expose
    private Boolean isRetired;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("reasonRetired")
    @Expose
    private Object reasonRetired;
    @SerializedName("questionId")
    @Expose
    private Integer questionId;
    @SerializedName("question")
    @Expose
    private Question question;
    @SerializedName("answerDataType")
    @Expose
    private String answerDataType;
    @SerializedName("correctOptionId")
    @Expose
    private Integer correctOptionId;
    @SerializedName("questionView")
    @Expose
    private String questionView;
    @SerializedName("questionScore")
    @Expose
    private Integer questionScore;
    @SerializedName("nextQuestion")
    @Expose
    private Object nextQuestion;
    @SerializedName("nextQuestionCriteriaRegex")
    @Expose
    private Object nextQuestionCriteriaRegex;
    @SerializedName("options")
    @Expose
    private List<Option> options = null;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsRetired() {
        return isRetired;
    }

    public void setIsRetired(Boolean isRetired) {
        this.isRetired = isRetired;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Object getReasonRetired() {
        return reasonRetired;
    }

    public void setReasonRetired(Object reasonRetired) {
        this.reasonRetired = reasonRetired;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerDataType() {
        return answerDataType;
    }

    public void setAnswerDataType(String answerDataType) {
        this.answerDataType = answerDataType;
    }

    public Integer getCorrectOptionId() {
        return correctOptionId;
    }

    public void setCorrectOptionId(Integer correctOptionId) {
        this.correctOptionId = correctOptionId;
    }

    public String getQuestionView() {
        return questionView;
    }

    public void setQuestionView(String questionView) {
        this.questionView = questionView;
    }

    public Integer getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Integer questionScore) {
        this.questionScore = questionScore;
    }

    public Object getNextQuestion() {
        return nextQuestion;
    }

    public void setNextQuestion(Object nextQuestion) {
        this.nextQuestion = nextQuestion;
    }

    public Object getNextQuestionCriteriaRegex() {
        return nextQuestionCriteriaRegex;
    }

    public void setNextQuestionCriteriaRegex(Object nextQuestionCriteriaRegex) {
        this.nextQuestionCriteriaRegex = nextQuestionCriteriaRegex;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
