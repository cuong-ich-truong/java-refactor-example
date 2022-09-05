package com.example.refactor.Domain;

/**
 * Dummy credit card.
 */
public class CreditCard extends PaymentBaseMethod {
    private String number;
    private String date;
    private String cvv;

    public CreditCard(String number, String date, String cvv) {
        super();

        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public String getLastForDigits() {
        return this.number.substring(this.number.length() - 4);
    }
}
