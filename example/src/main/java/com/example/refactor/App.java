package com.example.refactor;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App {
        public static void main(String[] args) {
                Payment payMethod = new Payment();

                Customer customerWithCc = new Customer("John Smith", "john.smith@example.com",
                                new CreditCard("1234", "12/22", "123"));
                payMethod.pay(BigDecimal.valueOf(23.43), customerWithCc,
                                PaymentMethod.CREDIT_CARD);

                payMethod.pay(BigDecimal.valueOf(121.12), customerWithCc,
                                PaymentMethod.CREDIT_CARD);

                Customer customerWithPp = new Customer("Allen Smith", "Allen.smith@example.com",
                                new PayPalAccount("john.smith@example.com", "paypal password"));
                payMethod.pay(BigDecimal.valueOf(15.56), customerWithPp,
                                PaymentMethod.PAYPAL);

                payMethod.pay(BigDecimal.valueOf(15.56), customerWithPp,
                                PaymentMethod.PAYPAL);

                Customer customerWithAp = new Customer("Tony Smith", "tony.smith@example.com",
                                new ApplePayAccount("john.smith@example.com", "123", "apply pay password"));
                payMethod.pay(BigDecimal.valueOf(15.56), customerWithAp,
                                PaymentMethod.APPLE_PAY);

                payMethod.pay(BigDecimal.valueOf(15.56), customerWithAp,
                                PaymentMethod.APPLE_PAY);
        }
}
