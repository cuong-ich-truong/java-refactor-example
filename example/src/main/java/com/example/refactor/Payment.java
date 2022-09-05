package com.example.refactor;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

        if (payMethod == PaymentMethod.CREDIT_CARD) {
            CreditCard creditCard = new CreditCard(cardNumber, cardDate, cardCvv);
            invoice = creditCard.processPayment(customerName, amount);
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

        } else if (payMethod == PaymentMethod.PAYPAL) {
            PayPalAccount payPalAccount = new PayPalAccount(customerEmail);
            payPalAccount.signIn(paypalPassword);
            invoice = payPalAccount.pay(customerName, amount);
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

        } else if (payMethod == PaymentMethod.APPLE_PAY) {
            ApplePayAccount applePayAccount = new ApplePayAccount(customerEmail, appleId);
            applePayAccount.signIn(applePayPassword);
            invoice = applePayAccount.pay(customerName, amount);
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
        return invoice;
    }
}
