package com.example.refactor.Domain;

import java.math.BigDecimal;

public abstract class PaymentAccount extends PaymentBaseMethod {
    protected String PASSWORD;
    private String email;
    private String password;
    private boolean signedIn;

    public boolean isSignedIn() {
        return signedIn;
    }

    public PaymentAccount(String email, String password, String storedPassword) {
        super();

        this.PASSWORD = storedPassword;
        this.signedIn = false;
        this.email = email;
        this.password = password;
    }

    public void signIn() {
        if (email.length() > 0 && PASSWORD == this.password) {
            signedIn = true;
        } else {
            signedIn = false;
        }
    }

    @Override
    public boolean deductAmount(BigDecimal amount) {
        if (!signedIn) {
            return false;
        }

        return super.deductAmount(amount);
    }
}
