package com.epam.restaurant.data;

public class DataException extends Exception {

    public DataException(Exception exception) {

        super(exception);
    }

}