package com.example.refactor.Domain;

public class PayPalAccount extends PaymentAccount {

    public PayPalAccount(String email, String password) {
        super(email, password, "paypal password");
    }

}
