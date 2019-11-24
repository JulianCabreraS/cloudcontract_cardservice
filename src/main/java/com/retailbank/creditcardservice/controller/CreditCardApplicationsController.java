package com.retailbank.creditcardservice.controller;

import com.retailbank.creditcardservice.gateway.CreditCheckRequest;
import com.retailbank.creditcardservice.gateway.CreditCheckResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static com.retailbank.creditcardservice.controller.ApplyForCreditCardResponse.Status.DENIED;
import static com.retailbank.creditcardservice.controller.ApplyForCreditCardResponse.Status.GRANTED;

@RestController
public class CreditCardApplicationsController {

    private final RestTemplate restTemplate;
    private final String creditcheckserviceBaseUrl;

    public CreditCardApplicationsController(RestTemplate restTemplate,
                                            @Value("${creditcheckservice.baseurl}") String creditcheckserviceBaseUrl) {
        this.restTemplate = restTemplate;
        this.creditcheckserviceBaseUrl=creditcheckserviceBaseUrl;
    }

    @PostMapping("/credit-card-applications")
    public ApplyForCreditCardResponse applyForCreditCard(@RequestBody final ApplyForCreditCardRequest applyForCreditCardRequest) {
        final int citizenNumber = applyForCreditCardRequest.getCitizenNumber();
        final String uri = UriComponentsBuilder.fromHttpUrl(creditcheckserviceBaseUrl)
                .path("credit-scores")
                .toUriString();
        final CreditCheckRequest request = new CreditCheckRequest(citizenNumber);
        final CreditCheckResponse creditCheckResponse = restTemplate.postForObject(uri, request, CreditCheckResponse.class);

        if(!creditCheckResponse.getUuid().equals(request.getUuid())){
            throw new RuntimeException("if these don't match something horrible happen");
        }

        final String uuid = creditCheckResponse.getUuid();
        if (applyForCreditCardRequest.getCardType() == ApplyForCreditCardRequest.CardTyppe.GOLD) {

            final CreditCheckResponse.Score score = creditCheckResponse.getScore();

            if(score == CreditCheckResponse.Score.HIGH){
                return new ApplyForCreditCardResponse(GRANTED, uuid);
            }else if (score == CreditCheckResponse.Score.LOW){
                return new ApplyForCreditCardResponse(DENIED, uuid);
            }


        }
        throw new RuntimeException("Card and score not yet implement");
    }
}
