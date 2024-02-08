package com.Strpe.Stripe.payloaad;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data
public class StripeTokenDto {


    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String cvc;

}
