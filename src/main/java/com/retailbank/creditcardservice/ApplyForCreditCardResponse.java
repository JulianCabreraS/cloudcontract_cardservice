package com.retailbank.creditcardservice;

public class ApplyForCreditCardResponse {
    private final Status status;

    public ApplyForCreditCardResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        GRANTED
    }
}
