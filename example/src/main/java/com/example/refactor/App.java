package com.example.refactor;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App {
        public static void main(String[] args) {
                Payment payMethod = new Payment();

                payMethod.pay(BigDecimal.valueOf(23.43), "Allen Wu", "", "", "424242-424242-424242", "11/24", "123", "",
                                "",
                                PaymentMethod.CREDIT_CARD);

                payMethod.pay(BigDecimal.valueOf(121.12), "John Smith", "", "", "424242-424242-424242", "11/24", "123",
                                "", "",
                                PaymentMethod.CREDIT_CARD);

                payMethod.pay(BigDecimal.valueOf(15.56), "Bob Code", "bob.code@example.com",
                                "correct password",
                                "", "", "", "", "",
                                PaymentMethod.PAYPAL);

                payMethod.pay(BigDecimal.valueOf(15.56), "Jason Tao", "jason.tao@example.com",
                                "wrong password",
                                "", "", "", "", "",
                                PaymentMethod.PAYPAL);

                payMethod.pay(BigDecimal.valueOf(15.56), "Beth Pony", "",
                                "",
                                "", "", "", "123", "strong password",
                                PaymentMethod.APPLE_PAY);

                payMethod.pay(BigDecimal.valueOf(15.56), "Dexter Fin", "",
                                "wrong password",
                                "", "", "", "456", "weak password",
                                PaymentMethod.APPLE_PAY);
        }
}
