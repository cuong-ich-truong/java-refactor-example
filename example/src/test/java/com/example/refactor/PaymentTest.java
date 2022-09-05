package com.example.refactor;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the Payment class.
 */
public class PaymentTest {

        private PaymentService paymentService;
        private Customer customerWithCc;
        private Customer customerWithPp;
        private Customer customerWithAp;

        @Before
        public void setUp() {
                paymentService = new PaymentService();
                customerWithCc = new Customer("John Smith", "john.smith@example.com",
                                new CreditCard("1234", "12/22", "123"));

                customerWithPp = new Customer("Allen Smith", "Allen.smith@example.com",
                                new PayPalAccount("john.smith@example.com", "paypal password"));

                customerWithAp = new Customer("Tony Smith", "tony.smith@example.com",
                                new ApplePayAccount("john.smith@example.com", "123", "apple pay password"));
        }

        @Test
        public void creditCard_ShouldCreateInvoice_WithCorrectMethod() {

                Invoice invoice = paymentService.pay(BigDecimal.valueOf(23.43), customerWithCc,
                                PaymentMethod.CREDIT_CARD);

                assertEquals("should create invoice with correct payment method", PaymentMethod.CREDIT_CARD,
                                invoice.getPaymentMethod());
        }

        @Test
        public void creditCard_ShouldCreatePaidInvoice_withAmountBelowCardLimit() {
                PaymentService payMethod = new PaymentService();

                Invoice invoice = payMethod.pay(BigDecimal.valueOf(23.43), customerWithCc,
                                PaymentMethod.CREDIT_CARD);

                assertEquals("should create invoice with correct status", "paid", invoice.Status());
                assertEquals("should create invoice with correct status", "paid", invoice.Status());
        }

        @Test
        public void creditCard_shouldUnpaidInvoice_WhenAmountOverLimit() {
                PaymentService payMethod = new PaymentService();

                Invoice invoice = payMethod.pay(BigDecimal.valueOf(121.12), customerWithCc,
                                PaymentMethod.CREDIT_CARD);

                assertEquals("should create unpaid invoice with correct status", "unpaid", invoice.Status());
        }

        @Test
        public void payPal_ShouldCreateInvoice_WithCorrectMethod() {

                Invoice invoice = paymentService.pay(BigDecimal.valueOf(15.56), customerWithPp,
                                PaymentMethod.PAYPAL);

                assertEquals("should create invoice with correct payment method", PaymentMethod.PAYPAL,
                                invoice.getPaymentMethod());
        }

        @Test
        public void payPal_ShouldCreatePaidInvoice_withValidPaypalPassword() {

                Invoice invoice = paymentService.pay(BigDecimal.valueOf(15.56), customerWithPp,
                                PaymentMethod.PAYPAL);

                assertEquals("should create invoice with correct status", "paid", invoice.Status());
        }

        @Test
        public void payPal_ShouldCreateUnpaidInvoice_withInvalidPaypalPassword() {
                customerWithPp = new Customer("Allen Smith", "Allen.smith@example.com",
                                new PayPalAccount("john.smith@example.com", "wrong password"));

                Invoice invoice = paymentService.pay(BigDecimal.valueOf(15.56), customerWithPp,
                                PaymentMethod.PAYPAL);

                assertEquals("should create invoice with correct status", "unpaid", invoice.Status());
        }

        @Test
        public void applePay_ShouldCreatePaidInvoice_WithCorrectMethod() {

                Invoice invoice = paymentService.pay(BigDecimal.valueOf(15.56), customerWithAp,
                                PaymentMethod.APPLE_PAY);

                assertEquals("should create invoice with correct payment method", PaymentMethod.APPLE_PAY,
                                invoice.getPaymentMethod());
        }

        @Test
        public void applePay_ShouldCreatePaidInvoice_withValidApplePassword() {

                Invoice invoice = paymentService.pay(BigDecimal.valueOf(15.56), customerWithAp,
                                PaymentMethod.APPLE_PAY);

                assertEquals("should create invoice with correct status", "paid", invoice.Status());
        }

        @Test
        public void applePay_ShouldCreateUnpaidInvoice_withInvalidApplePassword() {
                customerWithAp = new Customer("Tony Smith", "tony.smith@example.com",
                                new ApplePayAccount("john.smith@example.com", "123", "wrong password"));

                Invoice invoice = paymentService.pay(BigDecimal.valueOf(15.56), customerWithAp,
                                PaymentMethod.APPLE_PAY);

                assertEquals("should create invoice with correct status", "unpaid", invoice.Status());
        }
}
