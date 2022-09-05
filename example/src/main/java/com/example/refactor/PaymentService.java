package com.example.refactor;

import java.math.BigDecimal;

import com.example.refactor.Log.PaidObserver;
import com.example.refactor.Log.TransactionObserver;
import com.example.refactor.Log.UnpaidObserver;
import com.example.refactor.Payment.PaymentFactory;
import com.example.refactor.Payment.PaymentStrategy;

public class PaymentService {

    public Invoice pay(BigDecimal amount,
            Customer customer,
            PaymentMethod paymentMethod) {

        PaymentFactory paymentFactory = new PaymentFactory();
        PaymentStrategy paymentStrategy = paymentFactory.create(paymentMethod);
        paymentStrategy.addObserver(new TransactionObserver());
        paymentStrategy.addObserver(new PaidObserver());
        paymentStrategy.addObserver(new UnpaidObserver());

        paymentStrategy.verifyPaymentDetails(customer);
        Invoice invoice = paymentStrategy.pay(amount);

        return invoice;
    }
}
