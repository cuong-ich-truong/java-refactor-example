package com.example.refactor;

import java.math.BigDecimal;

public class Invoice {
    private String id;
    private String customerName;
    private String customerEmail;
    private String appleId;
    private String lastFourDigits;
    private BigDecimal amount;
    private String date;
    private boolean isPaid;
    private PaymentMethod paymentMethod;

    public String getCustomerName() {
        return customerName;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public String Number() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPaymentDate() {
        return date;
    }

    public Invoice(
            String customerName,
            BigDecimal amount,
            String lastFourDigits,
            String date) {
        this.isPaid = false;
        this.id = "INV" + String.format("%06d", System.currentTimeMillis());
        this.customerName = customerName;
        this.lastFourDigits = lastFourDigits;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = PaymentMethod.CREDIT_CARD;
    }

    public Invoice(
            String customerName,
            String customerEmail,
            BigDecimal amount,
            String date) {
        this.isPaid = false;
        this.id = "INV" + String.format("%06d", System.currentTimeMillis());
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = PaymentMethod.PAYPAL;
    }

    public Invoice(
            String customerName,
            String customerEmail,
            String appleId,
            BigDecimal amount,
            String date) {
        this.isPaid = false;
        this.id = "INV" + String.format("%06d", System.currentTimeMillis());
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.appleId = appleId;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = PaymentMethod.APPLE_PAY;

    }

    public String Status() {
        if (isPaid) {
            return "paid";
        } else {
            return "unpaid";
        }
    }
}
