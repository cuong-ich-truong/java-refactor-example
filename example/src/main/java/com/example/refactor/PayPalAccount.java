package com.example.refactor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PayPalAccount {
    private String email;
    private final String password;
    private boolean signedIn;

    public PayPalAccount(String email) {
        this.email = email;
        password = "correct password";
        signedIn = false;
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
            Invoice invoice = new Invoice(customerName, this.email, amount,
                    LocalDateTime.now().toString());
            invoice.setPaid(true);

            return invoice;
        } else {
            Invoice invoice = new Invoice(customerName, this.email, amount,
                    LocalDateTime.now().toString());
            invoice.setPaid(false);

            return invoice;
        }
    }
}
