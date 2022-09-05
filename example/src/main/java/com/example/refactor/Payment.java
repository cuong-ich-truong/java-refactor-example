package com.example.refactor;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class Payment {
    public Invoice pay(BigDecimal amount,
            Customer customer,
            PaymentMethod payMethod) {
        Invoice invoice = null;

        switch (payMethod) {
            case CREDIT_CARD:
                invoice = payByCreditCard(amount, customer);
                return invoice;
            case PAYPAL:
                invoice = payByPayPal(amount, customer);
                return invoice;
            case APPLE_PAY:
                invoice = payByApplePay(amount, customer);
                return invoice;
            default:
                throw new IllegalArgumentException("Unsupported payment method");
        }

    }

    private Invoice payByApplePay(BigDecimal amount, Customer customer) {
        Invoice invoice;
        customer.getApplePayAccount().signIn();
        boolean result = customer.getApplePayAccount().deductAmount(amount);
        invoice = new Invoice(customer.getName(), customer.getEmail(), customer.getApplePayAccount().getAppleId(),
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

    private Invoice payByPayPal(BigDecimal amount, Customer customer) {
        Invoice invoice;
        customer.getPayPalAccount().signIn();
        boolean result = customer.getPayPalAccount().deductAmount(amount);
        invoice = new Invoice(customer.getName(), customer.getEmail(), amount,
                LocalDateTime.now().toString());
        invoice.setIsPaid(result);

        String historyRecord = "Payment: " + "Customer " + invoice.getCustomerName()
                + " has made a payment with amount="
                + invoice.getAmount() + " on " + invoice.getPaymentDate() + " using Paypal" + "\r\n";
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

    private Invoice payByCreditCard(BigDecimal amount, Customer customer) {
        Invoice invoice;
        boolean result = customer.getCreditCard().deductAmount(amount);
        invoice = new Invoice(customer.getName(), amount, customer.getCreditCard().getLastForDigits(),
                LocalDateTime.now().toString());
        invoice.setIsPaid(result);
        String historyRecord = "Payment: " + "Customer " + invoice.getCustomerName()
                + " has made a payment with amount="
                + invoice.getAmount() + " on " + invoice.getPaymentDate() + " using credit card" + "\r\n";
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
}
