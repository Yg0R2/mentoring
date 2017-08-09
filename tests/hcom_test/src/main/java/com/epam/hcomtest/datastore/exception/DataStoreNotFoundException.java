package com.epam.hcomtest.datastore.exception;

public class DataStoreNotFoundException extends RuntimeException {

    public DataStoreNotFoundException(String message) {
        super(message);
    }

}
