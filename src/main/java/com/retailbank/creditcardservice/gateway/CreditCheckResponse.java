package com.retailbank.creditcardservice.gateway;

public class CreditCheckResponse {
    private Score score;
    private String uuid;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public String getUuid() {
        return uuid;
    }

    public enum Score {
        HIGH, LOW
    }


}
