package com.epam.restaurant.main;

import com.epam.restaurant.data.*;
import com.epam.restaurant.data.VisitorThreadCreator;
import com.epam.restaurant.logic.Restaurant;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private final static String DATA_VISITORS = "visitors.txt";

    public static void main(String[] args) throws DataException {

        DataReader dataReader = new FileDataReader();
        VisitorCreator visitorCreator = new JsonVisitorCreator(dataReader);
        Restaurant restaurant = Restaurant.getInstance();
        VisitorThreadCreator visitorThreadCreator = new VisitorThreadCreator(visitorCreator, restaurant);

        List<Thread> visitorThreads = visitorThreadCreator.create(DATA_VISITORS);
        int threadPoolSize = visitorThreads.size();

        ExecutorService service = Executors.newFixedThreadPool(threadPoolSize);
        visitorThreads.forEach(visitorThread -> service.submit(visitorThread));
        service.shutdown();

    }

}