package com.example.refactor;

import java.math.BigDecimal;

public class ApplePayAccount {
    private final String PASSWORD = "apple pay password";
    private String email;
    private String password;
    private String appleId;

    public String getAppleId() {
        return appleId;
    }

    private BigDecimal balance;
    private boolean signedIn;

    public ApplePayAccount(String email, String appleId, String password) {
        this.password = "strong password";
        this.signedIn = false;
        this.balance = BigDecimal.valueOf(100.00);
        this.email = email;
        this.appleId = appleId;
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
