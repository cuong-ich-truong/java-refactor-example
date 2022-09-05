package com.example.refactor.Payment;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

import com.example.refactor.Customer;
import com.example.refactor.Invoice;

public class PayWithApplePay implements PaymentStrategy {

    private Customer customer;

    @Override
    public Invoice pay(BigDecimal amount) {

        boolean result = customer.getApplePayAccount().deductAmount(amount);
        Invoice invoice = new Invoice(customer.getName(), customer.getEmail(),
                customer.getApplePayAccount().getAppleId(),
                amount,
                LocalDateTime.now().toString());
        invoice.setIsPaid(result);

        String historyRecord = "Payment: " + "Customer " + invoice.getCustomerName()
                + " has made a payment with amount="
                + invoice.getAmount() + " on " + invoice.getPaymentDate() + " using ApplePay" + "\r\n";
        System.out.print(historyRecord);
        try {
            File historyFile = new File("paymentLog.txt");
            if (!historyFile.exists()) {
                historyFile.createNewFile();
            }
            Files.write(Paths.get("paymentLog.txt"), historyRecord.getBytes(), StandardOpenOption.APPEND);
            if (invoice.isPaid()) {
                String statusMessage = "Payment: " + "Invoice " + invoice.Number() + " - Collected amount of "
                        + invoice.getAmount()
                        + "\r\n";
                System.out.print(statusMessage);
                Files.write(Paths.get("paymentLog.txt"), statusMessage.getBytes(), StandardOpenOption.APPEND);
            } else {
                String statusMessage = "Payment: " + "Invoice " + invoice.Number()
                        + " - Cannot collect amount of " + invoice.getAmount()
                        + "\r\n";
                System.out.print(statusMessage);
                Files.write(Paths.get("paymentLog.txt"), statusMessage.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    @Override
    public boolean verifyPaymentDetails(Customer customer) {
        this.customer = customer;
        customer.getApplePayAccount().signIn();

        return true;
    }

}
