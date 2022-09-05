package com.example.refactor.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.refactor.Customer;
import com.example.refactor.Invoice;
import com.example.refactor.Log.PaymentObserver;

public abstract class PaymentStrategy {
    private List<PaymentObserver> observers = new ArrayList<>();

    public void addObserver(PaymentObserver observer) {
        this.observers.add(observer);
    }

    public void updateObservers(Invoice invoice) {
        for (PaymentObserver observer : observers) {
            observer.logUpdates(invoice);
        }
    }

    public void updateObservers(String message) {
        for (PaymentObserver observer : observers) {
            observer.logUpdates(message);
        }
    }

    public abstract Invoice pay(BigDecimal amount);

    public abstract boolean verifyPaymentDetails(Customer customer);

    public abstract String getStatement(Invoice invoice);
}
