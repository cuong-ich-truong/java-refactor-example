package com.example.refactor;

import java.math.BigDecimal;

public class PayPalAccount {
    private final String PASSWORD = "paypal password";
    private String email;
    private String password;
    private BigDecimal balance;
    private boolean signedIn;

    public boolean isSignedIn() {
        return signedIn;
    }

    public PayPalAccount(String email, String password) {
        this.balance = BigDecimal.valueOf(100.00);

        signedIn = false;
        this.email = email;
        this.password = password;
    }

    public void signIn() {
        if (PASSWORD == this.password) {
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
