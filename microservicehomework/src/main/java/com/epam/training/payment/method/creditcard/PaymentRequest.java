package com.epam.training.payment.method.creditcard;

import com.epam.training.payment.AbstractPaymentRequest;
import org.springframework.stereotype.Component;

public final class PaymentRequest extends AbstractPaymentRequest {

    private CreditCard creditCard;
    private Customer customer;

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
