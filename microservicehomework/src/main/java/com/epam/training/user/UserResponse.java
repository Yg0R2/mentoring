package com.epam.training.user;

import com.epam.training.response.AbstractResponse;

public final class UserResponse extends AbstractResponse {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
