
package com.ihsinformatics.korona.model.question;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizResponse {

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
    @SerializedName("activityId")
    @Expose
    private Integer activityId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("activityType")
    @Expose
    private ActivityType activityType;
    @SerializedName("category")
    @Expose
    private Object category;
    @SerializedName("dateStart")
    @Expose
    private Object dateStart;
    @SerializedName("dateEnd")
    @Expose
    private Object dateEnd;
    @SerializedName("questions")
    @Expose
    private List<Questions> questions = null;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("questionsOrder")
    @Expose
    private String questionsOrder;
    @SerializedName("decision")
    @Expose
    private String decision;
    @SerializedName("decisionMap")
    @Expose
    private DecisionMap decisionMap;
    @SerializedName("questionsOrderMap")
    @Expose
    private QuestionsOrderMap questionsOrderMap;
    @SerializedName("totalScore")
    @Expose
    private Integer totalScore;

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

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public Object getDateStart() {
        return dateStart;
    }

    public void setDateStart(Object dateStart) {
        this.dateStart = dateStart;
    }

    public Object getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Object dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getQuestionsOrder() {
        return questionsOrder;
    }

    public void setQuestionsOrder(String questionsOrder) {
        this.questionsOrder = questionsOrder;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public DecisionMap getDecisionMap() {
        return decisionMap;
    }

    public void setDecisionMap(DecisionMap decisionMap) {
        this.decisionMap = decisionMap;
    }

    public QuestionsOrderMap getQuestionsOrderMap() {
        return questionsOrderMap;
    }

    public void setQuestionsOrderMap(QuestionsOrderMap questionsOrderMap) {
        this.questionsOrderMap = questionsOrderMap;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

}
