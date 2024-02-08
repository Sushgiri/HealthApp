package com.Strpe.Stripe.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Entity
@Table(name = "Paymnets")
@Data
public class PaymentRecords {
    @Id
    private String paymentId;
    private String useremail;
    private String username;
    private String dateofpayment;
    private double Amount;

}
