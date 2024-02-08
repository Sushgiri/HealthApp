package com.Strpe.Stripe.service;

import com.Strpe.Stripe.entity.PaymentRecords;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {


    public byte[] generatePdf(PaymentRecords paymentRecords) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.newLineAtOffset(20, 700);

                contentStream.showText(paymentRecords.getPaymentId());
                contentStream.newLine();
                contentStream.showText(paymentRecords.getUsername());
                contentStream.newLine();
                contentStream.showText(paymentRecords.getDateofpayment());
                contentStream.newLine();
                String amountpaid = String.valueOf(paymentRecords.getAmount());
                contentStream.showText(amountpaid);
                contentStream.endText();
            } catch (Exception e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            document.close();

            return baos.toByteArray();
        }
    }
    }