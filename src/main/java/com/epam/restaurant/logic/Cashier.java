package com.epam.restaurant.logic;

import com.epam.restaurant.model.Visitor;

public class Cashier {

    private final int id;
    private int cash;

    public Cashier(int id) {

        this.id = id;

    }

    public void processOrder(Visitor visitor) {

        int orderSum = visitor.getOrderSum();
        visitor.setOrderSum(0);

        cash = cash + orderSum;

    }

}