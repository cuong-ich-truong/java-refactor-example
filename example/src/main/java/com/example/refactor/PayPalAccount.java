package com.example.refactor;

import java.math.BigDecimal;

public class PayPalAccount {
    private String email;
    private final String password;
    private BigDecimal balance;
    private boolean signedIn;

    public boolean isSignedIn() {
        return signedIn;
    }

    public PayPalAccount(String email) {
        this.balance = BigDecimal.valueOf(100.00);
        password = "correct password";
        signedIn = false;
        this.email = email;
    }

    public void signIn(String password) {
        if (password == this.password) {
            signedIn = true;
        } else {
            signedIn = false;
        }
    }

    public boolean deductAmount(BigDecimal amount) {
        if (!signedIn) {
            return false;
        }

        BigDecimal newBalance = this.balance.subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) >= 0) {
            this.balance = newBalance;
            return true;
        } else {
            return false;
        }
    }
}
