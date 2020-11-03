package com.epam.restaurant.data;

import com.epam.restaurant.logic.VisitorThread;
import com.epam.restaurant.logic.Restaurant;
import com.epam.restaurant.model.Visitor;

import java.util.ArrayList;
import java.util.List;

public class VisitorThreadCreator {

    private VisitorCreator visitorCreator;
    private Restaurant restaurant;

    public VisitorThreadCreator(VisitorCreator visitorCreator, Restaurant restaurant) {

        this.visitorCreator = visitorCreator;
        this.restaurant = restaurant;

    }

    public List<Thread> create(String fileName) throws DataException {

        List<Visitor> visitors = visitorCreator.create(fileName);

        List<Thread> visitorThreads = new ArrayList<>();

        for (Visitor visitor : visitors) {
            Thread visitorThread = new Thread(new VisitorThread(visitor, restaurant));
            visitorThreads.add(visitorThread);
        }

        return visitorThreads;
    }

}