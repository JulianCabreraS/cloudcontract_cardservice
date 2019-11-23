package com.retailbank.creditcardservice;

public class CreditCheckResponse {
    private Score score;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public enum Score {
        HIGH
    }


}
