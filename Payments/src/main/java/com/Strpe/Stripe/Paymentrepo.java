package com.Strpe.Stripe;

import com.Strpe.Stripe.entity.PaymentRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Paymentrepo extends JpaRepository<PaymentRecords,String>{
}
