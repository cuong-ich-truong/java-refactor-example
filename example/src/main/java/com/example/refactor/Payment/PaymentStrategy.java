package com.example.refactor.Payment;

import java.math.BigDecimal;

import com.example.refactor.Customer;
import com.example.refactor.Invoice;

public interface PaymentStrategy {
    Invoice pay(BigDecimal amount);

    boolean verifyPaymentDetails(Customer customer);
}
