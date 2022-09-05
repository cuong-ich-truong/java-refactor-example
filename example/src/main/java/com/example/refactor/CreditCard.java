package com.example.refactor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Dummy credit card.
 */
public class CreditCard {
    private String number;
    private BigDecimal limit;
    private String date;
    private String cvv;

    CreditCard(String number, String date, String cvv) {
        this.limit = BigDecimal.valueOf(100.00);
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public Invoice processPayment(String customerName, BigDecimal amount) {
        if (amount.compareTo(limit) > 0) {
            Invoice invoice = new Invoice(customerName, amount, this.number.substring(this.number.length() - 4),
                    LocalDateTime.now().toString());
            invoice.setPaid(false);
            return invoice;
        } else {
            Invoice invoice = new Invoice(customerName, amount, this.number.substring(this.number.length() - 4),
                    LocalDateTime.now().toString());
            invoice.setPaid(true);
            return invoice;
        }
    }
}
