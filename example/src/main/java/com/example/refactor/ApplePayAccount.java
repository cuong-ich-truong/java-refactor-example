package com.example.refactor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ApplePayAccount {
    private String email;
    private String appleId;
    private final String password;
    private boolean signedIn;

    public ApplePayAccount(String email, String appleId) {
        this.email = email;
        this.appleId = appleId;
        this.password = "strong password";
        this.signedIn = false;
    }

    public void signIn(String password) {
        if (password == this.password) {
            signedIn = true;
        } else {
            signedIn = false;
        }
    }

    public Invoice pay(String customerName, BigDecimal amount) {
        if (signedIn) {
            Invoice invoice = new Invoice(customerName, email, this.appleId, amount,
                    LocalDateTime.now().toString());
            invoice.setPaid(true);

            return invoice;
        } else {
            Invoice invoice = new Invoice(customerName, email, this.appleId, amount,
                    LocalDateTime.now().toString());
            invoice.setPaid(false);

            return invoice;
        }
    }
}
