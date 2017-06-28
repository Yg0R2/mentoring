package com.epam.training.payment.method.creditcard;

import com.epam.training.payment.AbstractPaymentResponse;

public final class PaymentResponse extends AbstractPaymentResponse {

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
