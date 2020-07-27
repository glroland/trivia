package com.glroland.trivia.svc;

public class Answer {
    private String value;
    private boolean correctFlag;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCorrectFlag() {
        return correctFlag;
    }

    public void setCorrectFlag(boolean correctFlag) {
        this.correctFlag = correctFlag;
    }
}