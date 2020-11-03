package com.epam.restaurant.logic;

public class RestaurantException extends Exception {

    public RestaurantException(Exception exception) {

        super(exception);
    }

}