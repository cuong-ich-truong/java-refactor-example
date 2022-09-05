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
            String customerName,
            String customerEmail,
            String paypalPassword,
            String cardNumber,
            String cardDate,
            String cardCvv,
            String appleId,
            String applePayPassword,
            PaymentMethod payMethod) {
        Invoice invoice = null;

        switch (payMethod) {
            case CREDIT_CARD:
                invoice = payByCreditCard(amount, customerName, cardNumber, cardDate, cardCvv);
                return invoice;
            case PAYPAL:
                invoice = payByPayPal(amount, customerName, customerEmail, paypalPassword);
                return invoice;
            case APPLE_PAY:
                invoice = payByApplePay(amount, customerName, customerEmail, appleId, applePayPassword);
                return invoice;
            default:
                throw new IllegalArgumentException("Unsupported payment method");
        }

    }

    private Invoice payByApplePay(BigDecimal amount, String customerName, String customerEmail, String appleId,
            String applePayPassword) {
        Invoice invoice;
        ApplePayAccount applePayAccount = new ApplePayAccount(customerEmail, appleId);
        applePayAccount.signIn(applePayPassword);
        boolean result = applePayAccount.deductAmount(amount);
        invoice = new Invoice(customerName, customerEmail, appleId, amount,
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

    private Invoice payByPayPal(BigDecimal amount, String customerName, String customerEmail, String paypalPassword) {
        Invoice invoice;
        PayPalAccount payPalAccount = new PayPalAccount(customerEmail);
        payPalAccount.signIn(paypalPassword);
        boolean result = payPalAccount.deductAmount(amount);
        invoice = new Invoice(customerName, customerEmail, amount,
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

    private Invoice payByCreditCard(BigDecimal amount, String customerName, String cardNumber, String cardDate,
            String cardCvv) {
        Invoice invoice;
        CreditCard creditCard = new CreditCard(cardNumber, cardDate, cardCvv);
        boolean result = creditCard.deductAmount(amount);
        invoice = new Invoice(customerName, amount, creditCard.getLastForDigits(),
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
