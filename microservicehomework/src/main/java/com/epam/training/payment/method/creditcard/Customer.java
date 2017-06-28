package com.epam.training.payment.method.creditcard;

import java.util.Arrays;

public final class Customer {

    private String address;
    private Integer[] dateOfBirth;
    private String emailAddress;
    private String firstName;
    private String lastName;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer[] getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfBirthValue() {
        String[] dateOfBirthValues = Arrays.stream(dateOfBirth).map(String::valueOf).toArray(String[]::new);

        return String.join("-", dateOfBirthValues);
    }

    public void setDateOfBirth(Integer[] dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
