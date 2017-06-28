package com.epam.training.user;

import com.epam.training.request.AbstractRequest;

public final class UserRequest extends AbstractRequest {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
