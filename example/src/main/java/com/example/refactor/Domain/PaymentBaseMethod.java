package com.example.refactor.Domain;

import java.math.BigDecimal;

public abstract class PaymentBaseMethod {
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public PaymentBaseMethod() {
        this.balance = BigDecimal.valueOf(100.00);
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
}
