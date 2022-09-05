package com.example.refactor;

import java.math.BigDecimal;

import com.example.refactor.Payment.PaymentFactory;
import com.example.refactor.Payment.PaymentStrategy;

public class PaymentService {

    public Invoice pay(BigDecimal amount,
            Customer customer,
            PaymentMethod paymentMethod) {

        PaymentFactory paymentFactory = new PaymentFactory();
        PaymentStrategy paymentStrategy = paymentFactory.create(paymentMethod);

        paymentStrategy.verifyPaymentDetails(customer);
        Invoice invoice = paymentStrategy.pay(amount);
        return invoice;
    }
}
