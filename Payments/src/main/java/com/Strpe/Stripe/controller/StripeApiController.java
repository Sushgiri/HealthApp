package com.Strpe.Stripe.controller;

import com.Strpe.Stripe.Paymentrepo;
import com.Strpe.Stripe.entity.PaymentRecords;
import com.Strpe.Stripe.payloaad.PaymentRequestDto;
import com.Strpe.Stripe.service.PdfService;
import com.Strpe.Stripe.service.StripeService;
import com.Strpe.Stripe.payloaad.StripeTokenDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class StripeApiController {
    private final StripeService stripeService;

    @Autowired
    private Paymentrepo paymentrepo;

    @Autowired
    private JavaMailSender javaMailSender;

    //http://localhost:8090/payments/card/token
    @PostMapping("/card/token")
    public String createCardToken( @RequestBody StripeTokenDto model) {
        return stripeService.createToken(model.getCardNumber(), model.getExpMonth(), model.getExpYear(), model.getCvc());
    }

    @PostMapping("/charge/{username}/{useremail}/{amount}")
    public String charge(@PathVariable String username,@PathVariable String useremail,@PathVariable String amount, @RequestBody PaymentRequestDto paymentRequestDto) throws StripeException {
       double receivedamount = Double.parseDouble(amount);
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount((long) receivedamount)
                        .setCurrency(paymentRequestDto.getCurrency())
                        .setPaymentMethod(paymentRequestDto.getTokenId())

                        .build();
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        if (paymentIntent != null) {

                PaymentRecords paymentRecords = new PaymentRecords();
                paymentRecords.setAmount(receivedamount);
                String pid = UUID.randomUUID().toString();
                paymentRecords.setPaymentId(pid);
                Date date = new Date();
                String todydate = date.toString();
                paymentRecords.setDateofpayment(todydate);
                paymentRecords.setUsername(username);
                paymentRecords.setUseremail(useremail);
                paymentrepo.save(paymentRecords);
                generateAndEmailPdf(paymentRecords);
                return "payment successful";


        }

        return null;
    }
    @Autowired
    private PdfService pdfService;
    public ResponseEntity<ByteArrayResource> generateAndEmailPdf(PaymentRecords paymentRecords) {
        try {
            byte[] pdfBytes = pdfService.generatePdf(paymentRecords);

            // Attach the PDF to an email
            sendEmailWithAttachment(pdfBytes,paymentRecords);

            ByteArrayResource resource = new ByteArrayResource(pdfBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Reciept.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
            return ResponseEntity.badRequest().build();
        }
    }
    private void sendEmailWithAttachment(byte[] pdfBytes,PaymentRecords paymentRecords) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setSubject("PDF Attachment");
            helper.setText("Payment  Reciept For Medicine Order");

            // Set the User email address
            helper.setTo(paymentRecords.getUseremail());

            // Attach the PDF
            helper.addAttachment("Reciept.pdf", new ByteArrayResource(pdfBytes));

            // Send the email
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }
}







