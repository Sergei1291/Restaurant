package com.epam.restaurant.logic;

import com.epam.restaurant.model.Visitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VisitorThread implements Runnable {

    private final static Logger LOGGER = LogManager.getLogger();

    private final Restaurant restaurant;
    private final Visitor visitor;

    public VisitorThread(Visitor visitor, Restaurant restaurant) {

        this.visitor = visitor;
        this.restaurant = restaurant;

    }

    @Override
    public void run() {

        try {

            Cashier cashier = restaurant.acquireCashier();
            cashier.processOrder(visitor);
            restaurant.releaseCashier(cashier);

        } catch (RestaurantException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

}