package com.epam.training.payment.method.payfriend;

import com.epam.training.payment.AbstractPaymentResponse;

public final class PayFriendResponse extends AbstractPaymentResponse {

    private PayFriend payFriend;
    private PayFriend targetFriend;

    public PayFriend getPayFriend() {
        return payFriend;
    }

    public void setPayFriend(PayFriend payFriend) {
        this.payFriend = payFriend;
    }

    public PayFriend getTargetFriend() {
        return targetFriend;
    }

    public void setTargetFriend(PayFriend targetFriend) {
        this.targetFriend = targetFriend;
    }

}
