package com.retailbank.creditcardservice.gateway;

import java.time.LocalDate;
import java.util.UUID;

public class CreditCheckRequest {
    private final String requestedDate = LocalDate.now().toString();
    private final String uuid = UUID.randomUUID().toString();
    private int citizenNumber;

    public CreditCheckRequest(int citizenNumber) {

        this.citizenNumber = citizenNumber;
    }

    public int getCitizenNumber() {
        return citizenNumber;
    }

    public String getRequestedDate() {
        return requestedDate;
    }

    public String getUuid() {
        return uuid;
    }
}
