package com.glroland.trivia.questions.gateway.getquestions;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TriviaQuestions 
{
    @JsonProperty("response_code")
    private int responseCode;

    @JsonProperty("results")
    private List<TriviaQuestion> questions;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<TriviaQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TriviaQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "{\n" +
            "responseCode=" + responseCode +
            ",\nquestions='" + questions + '\'' +
            "\n}";
    }
}
