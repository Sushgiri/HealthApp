package com.Strpe.Stripe.payloaad;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data

public class PaymentRequestDto {
    private String currency;
    private  String tokenId;

}
