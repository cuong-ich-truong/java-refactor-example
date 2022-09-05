package com.example.refactor;

import com.example.refactor.Domain.ApplePayAccount;
import com.example.refactor.Domain.CreditCard;
import com.example.refactor.Domain.PayPalAccount;

public class Customer {
    private String name;

    public Customer(String name, String email, CreditCard creditCard) {
        this.name = name;
        this.email = email;
        this.creditCard = creditCard;
    }

    public Customer(String name, String email, PayPalAccount payPalAccount) {
        this.name = name;
        this.email = email;
        this.payPalAccount = payPalAccount;
    }

    public Customer(String name, String email,
            ApplePayAccount applePayAccount) {
        this.name = name;
        this.email = email;
        this.applePayAccount = applePayAccount;
    }

    public String getName() {
        return name;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    private CreditCard creditCard;

    public CreditCard getCreditCard() {
        return creditCard;
    }

    private PayPalAccount payPalAccount;

    public PayPalAccount getPayPalAccount() {
        return payPalAccount;
    }

    private ApplePayAccount applePayAccount;

    public ApplePayAccount getApplePayAccount() {
        return applePayAccount;
    }
}
