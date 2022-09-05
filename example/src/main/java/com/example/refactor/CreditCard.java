package com.example.refactor;

import java.math.BigDecimal;

/**
 * Dummy credit card.
 */
public class CreditCard {
    private String number;
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    private String date;
    private String cvv;

    CreditCard(String number, String date, String cvv) {
        this.balance = BigDecimal.valueOf(100.00);
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public boolean deductAmount(BigDecimal amount) {
        BigDecimal newBalance = this.balance.subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
            this.balance = newBalance;
            return true;
        } else {
            return false;
        }
    }

    public String getLastForDigits() {
        return this.number.substring(this.number.length() - 4);
    }
}
