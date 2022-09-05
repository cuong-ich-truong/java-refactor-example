package com.example.refactor.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.refactor.Customer;
import com.example.refactor.Invoice;

public class PayWithCreditCard extends PaymentStrategy {

    private Customer customer;

    @Override
    public Invoice pay(BigDecimal amount) {
        boolean result = customer.getCreditCard().deductAmount(amount);
        Invoice invoice = new Invoice(customer.getName(), amount, customer.getCreditCard().getLastForDigits(),
                LocalDateTime.now().toString());
        invoice.setIsPaid(result);

        updateObservers(this.getStatement(invoice));
        updateObservers(invoice);

        return invoice;
    }

    @Override
    public boolean verifyPaymentDetails(Customer customer) {
        this.customer = customer;

        return true;
    }

    @Override
    public String getStatement(Invoice invoice) {
        String statement = "Customer " + invoice.getCustomerName()
                + " has made a payment with amount="
                + invoice.getAmount() + " on " + invoice.getPaymentDate() + " using credit card" + "\r\n";

        return statement;
    }
}
