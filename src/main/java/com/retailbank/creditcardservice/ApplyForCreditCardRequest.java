package com.retailbank.creditcardservice;

public class ApplyForCreditCardRequest {
    private int citizenNumber;
    private CardTyppe cardType;

    public int getCitizenNumber() {
        return citizenNumber;
    }

    public void setCitizenNumber(int citizenNumber) {
        this.citizenNumber = citizenNumber;
    }

    public CardTyppe getCardType() {
        return cardType;
    }

    public void setCardType(CardTyppe cardType) {
        this.cardType = cardType;
    }

    public enum CardTyppe {
        GOLD
    }

}
