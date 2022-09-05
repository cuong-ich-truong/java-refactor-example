package com.example.refactor.Log;

import com.example.refactor.Invoice;

public class TransactionObserver implements PaymentObserver {

    @Override
    public void logUpdates(Invoice invoice) {
        String historyRecord = "Payment: " + "Customer " + invoice.getCustomerName()
                + " has made a payment with amount="
                + invoice.getAmount() + " on " + invoice.getPaymentDate() + " using credit card" + "\r\n";

        PaymentLogger.logMessage("paymentLog.txt", historyRecord);
    }

    @Override
    public void logUpdates(String messages) {
        // Not interested
    }

}
