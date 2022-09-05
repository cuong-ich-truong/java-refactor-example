package com.example.refactor.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.refactor.Customer;
import com.example.refactor.Invoice;

public class PayWithPaypal extends PaymentStrategy {

    private Customer customer;

    @Override
    public Invoice pay(BigDecimal amount) {
        boolean paymentResult = customer.getPayPalAccount().deductAmount(amount);
        Invoice invoice = new Invoice(customer.getName(), customer.getEmail(), amount,
                LocalDateTime.now().toString());
        invoice.setIsPaid(paymentResult);

        updateObservers(this.getStatement(invoice));
        updateObservers(invoice);

        return invoice;
    }

    @Override
    public boolean verifyPaymentDetails(Customer customer) {
        this.customer = customer;
        customer.getPayPalAccount().signIn();

        return true;
    }

    @Override
    public String getStatement(Invoice invoice) {
        String statement = "Customer " + invoice.getCustomerName()
                + " has made a payment with amount="
                + invoice.getAmount() + " on " + invoice.getPaymentDate() + " using Paypal" + "\r\n";

        return statement;
    }
}
