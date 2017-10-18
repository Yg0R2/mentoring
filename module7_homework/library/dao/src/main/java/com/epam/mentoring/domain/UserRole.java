package com.epam.mentoring.domain;

public enum UserRole {

    USER, LIBRARIAN, ADMIN;

    public boolean isAdmin() {
        return this.equals(ADMIN);
    }

    public boolean isLibrarian() {
        return this.equals(LIBRARIAN);
    }

    public boolean isUser() {
        return this.equals(USER);
    }

}
