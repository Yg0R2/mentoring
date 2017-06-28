package com.epam.training.payment;

import com.epam.training.response.AbstractResponse;

public abstract class AbstractPaymentResponse extends AbstractResponse {

    private double amount;
    private Integer[] paymentTime;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer[] getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Integer[] paymentTime) {
        this.paymentTime = paymentTime;
    }

}
