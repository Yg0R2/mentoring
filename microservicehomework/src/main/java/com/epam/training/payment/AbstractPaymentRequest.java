package com.epam.training.payment;

import com.epam.training.request.AbstractRequest;

public abstract class AbstractPaymentRequest extends AbstractRequest {

    private Payment payment;

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
