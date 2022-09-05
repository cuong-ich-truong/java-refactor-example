package com.example.refactor.Domain;

public class ApplePayAccount extends PaymentAccount {
    private String appleId;

    public String getAppleId() {
        return appleId;
    }

    public ApplePayAccount(String email, String appleId, String password) {
        super(email, password, "apple pay password");

        this.appleId = appleId;
    }
}
