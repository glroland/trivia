package com.glroland.trivia.svc.getquestions;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TriviaQuestion {

    @JsonProperty("category")
    private String categoryName;

    @JsonProperty("type")
    private String type;

    @JsonProperty("difficulty")
    private String difficulty;

    @JsonProperty("question")
    private String question;

    @JsonProperty("correct_answer")
    private String correctAnswer;

    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswer() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswer(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    @Override
    public String toString() {
        return "{\n" +
            "category=" + categoryName +
            ",\ntype='" + type + '\'' +
            ",\ndifficulty='" + difficulty + '\'' +
            ",\nquestion='" + question + '\'' +
            ",\ncorrect_answer='" + correctAnswer + '\'' +
            ",\nincorrect_answers='" + incorrectAnswers + '\'' +
            "\n}";


    }
}
