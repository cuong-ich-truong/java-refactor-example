package com.example.refactor.Payment;

import com.example.refactor.PayWithApplePay;
import com.example.refactor.PaymentMethod;

public class PaymentFactory {
    public PaymentStrategy create(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case CREDIT_CARD:
                return new PayWithCreditCard();
            case PAYPAL:
                return new PayWithPaypal();
            case APPLE_PAY:
                return new PayWithApplePay();
            default:
                throw new IllegalArgumentException("Unsupported payment method");
        }
    }
}
