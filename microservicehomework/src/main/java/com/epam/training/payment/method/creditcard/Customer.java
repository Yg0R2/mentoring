package com.epam.training.payment.method.creditcard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

public final class Customer {

    private String address;
    @JsonIgnore
    private String dateOfBirth;
    private String emailAddress;
    private String firstName;
    private String lastName;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty
    public void setDateOfBirth(Integer[] dateOfBirth) {
        String[] dateOfBirthValues = Arrays.stream(dateOfBirth).map(String::valueOf).toArray(String[]::new);

        this.dateOfBirth = String.join("-", dateOfBirthValues);
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
