package com.example.refactor.Log;

import com.example.refactor.Invoice;

public interface PaymentObserver {

    void logUpdates(Invoice invoice);

    void logUpdates(String messages);
}
