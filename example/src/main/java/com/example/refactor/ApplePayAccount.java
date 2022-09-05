package com.example.refactor;

import java.math.BigDecimal;

public class ApplePayAccount {
    private String email;
    private String appleId;
    private final String password;
    private BigDecimal balance;
    private boolean signedIn;

    public ApplePayAccount(String email, String appleId) {
        this.password = "strong password";
        this.signedIn = false;
        this.balance = BigDecimal.valueOf(100.00);
        this.email = email;
        this.appleId = appleId;
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
