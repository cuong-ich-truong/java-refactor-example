package com.example.refactor.Log;

import com.example.refactor.Invoice;

public class PaidObserver implements PaymentObserver {

    @Override
    public void logUpdates(Invoice invoice) {
        if (invoice.isPaid()) {
            String statusMessage = "Payment: " + "Invoice " + invoice.Number() + " - Collected amount of "
                    + invoice.getAmount()
                    + "\r\n";

            PaymentLogger.logMessage("paymentLog.txt", statusMessage);
        }
    }

    @Override
    public void logUpdates(String messages) {
        // not interested
    }

}
