package com.example.refactor;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Unit test for the Payment class.
 */
public class PaymentTest {

    @Test
    public void creditCard_ShouldCreateInvoice_WithCorrectMethod() {
        Payment payMethod = new Payment();

        Invoice invoice = payMethod.pay(BigDecimal.valueOf(23.43), "Allen Wu", "", "", "424242-424242-424242", "11/24",
                "123", "",
                "",
                PaymentMethod.CREDIT_CARD);

        assertEquals("should create invoice with correct payment method", PaymentMethod.CREDIT_CARD,
                invoice.getPaymentMethod());
    }

    @Test
    public void creditCard_ShouldCreatePaidInvoice_withAmountBelowCardLimit() {
        Payment payMethod = new Payment();

        Invoice invoice = payMethod.pay(BigDecimal.valueOf(23.43), "Allen Wu", "", "", "424242-424242-424242", "11/24",
                "123", "",
                "",
                PaymentMethod.CREDIT_CARD);

        assertEquals("should create invoice with correct status", "paid", invoice.Status());
        assertEquals("should create invoice with correct status", "paid", invoice.Status());
    }

    @Test
    public void creditCard_shouldUnpaidInvoice_WhenAmountOverLimit() {
        Payment payMethod = new Payment();

        Invoice invoice = payMethod.pay(BigDecimal.valueOf(121.12), "John Smith", "", "", "424242-424242-424242",
                "11/24", "123",
                "", "",
                PaymentMethod.CREDIT_CARD);

        assertEquals("should create unpaid invoice with correct status", "unpaid", invoice.Status());
    }

    @Test
    public void payPal_ShouldCreateInvoice_WithCorrectMethod() {
        Payment payMethod = new Payment();

        Invoice invoice = payMethod.pay(BigDecimal.valueOf(15.56), "Bob Code", "bob.code@example.com",
                "correct password",
                "", "", "", "", "",
                PaymentMethod.PAYPAL);

        assertEquals("should create invoice with correct payment method", PaymentMethod.PAYPAL,
                invoice.getPaymentMethod());
    }

    @Test
    public void payPal_ShouldCreatePaidInvoice_withValidPaypalPassword() {
        Payment payMethod = new Payment();

        Invoice invoice = payMethod.pay(BigDecimal.valueOf(15.56), "Bob Code", "bob.code@example.com",
                "correct password",
                "", "", "", "", "",
                PaymentMethod.PAYPAL);

        assertEquals("should create invoice with correct status", "paid", invoice.Status());
    }

    @Test
    public void payPal_ShouldCreateUnpaidInvoice_withInvalidPaypalPassword() {
        Payment payMethod = new Payment();

        Invoice invoice = payMethod.pay(BigDecimal.valueOf(15.56), "Jason Tao", "jason.tao@example.com",
                "wrong password",
                "", "", "", "", "",
                PaymentMethod.PAYPAL);

        assertEquals("should create invoice with correct status", "unpaid", invoice.Status());
    }

    @Test
    public void applePay_ShouldCreatePaidInvoice_WithCorrectMethod() {
        Payment payMethod = new Payment();

        Invoice invoice = payMethod.pay(BigDecimal.valueOf(15.56), "Dexter Fin", "",
                "wrong password",
                "", "", "", "456", "strong password",
                PaymentMethod.APPLE_PAY);

        assertEquals("should create invoice with correct payment method", PaymentMethod.APPLE_PAY,
                invoice.getPaymentMethod());
    }

    @Test
    public void applePay_ShouldCreatePaidInvoice_withValidApplePassword() {
        Payment payMethod = new Payment();

        Invoice invoice = payMethod.pay(BigDecimal.valueOf(15.56), "Dexter Fin", "",
                "wrong password",
                "", "", "", "456", "strong password",
                PaymentMethod.APPLE_PAY);

        assertEquals("should create invoice with correct status", "paid", invoice.Status());
    }

    @Test
    public void applePay_ShouldCreateUnpaidInvoice_withInvalidApplePassword() {
        Payment payMethod = new Payment();

        Invoice invoice = payMethod.pay(BigDecimal.valueOf(15.56), "Dexter Fin", "",
                "wrong password",
                "", "", "", "456", "weak password",
                PaymentMethod.APPLE_PAY);

        assertEquals("should create invoice with correct status", "unpaid", invoice.Status());
    }
}
