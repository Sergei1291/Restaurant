package com.epam.restaurant.logic;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {

    private static final ReentrantLock LOCK_SINGLETON = new ReentrantLock();
    private static final ReentrantLock LOCK_CASHIERS = new ReentrantLock();

    private static final int CASHIERS_NUMBER = 3;

    private final static AtomicBoolean isInstanceCreated = new AtomicBoolean(false);

    private static Restaurant instance;

    public static Restaurant getInstance() {

        if (!isInstanceCreated.get()) {

            LOCK_SINGLETON.lock();

            try {
                Restaurant localInstance = instance;

                if (localInstance == null) {
                    localInstance = new Restaurant();
                    localInstance.initialize();
                    instance = localInstance;
                    isInstanceCreated.set(true);
                }

            } finally {
                LOCK_SINGLETON.unlock();
            }

        }

        return instance;
    }

    private Queue<Cashier> cashiers;
    private Semaphore semaphore;

    private Restaurant() {
    }

    private void initialize() {

        this.cashiers = initializeCashiers(CASHIERS_NUMBER);

        int sizeCashiers = cashiers.size();
        this.semaphore = new Semaphore(sizeCashiers, true);

    }

    private Queue<Cashier> initializeCashiers(final int CASHIERS_NUMBER) {

        Queue<Cashier> cashiers = new LinkedList<>();

        for (int i = 0; i < CASHIERS_NUMBER; i++) {
            int currentId = i + 1;
            Cashier cashier = new Cashier(currentId);
            cashiers.offer(cashier);
        }

        return cashiers;
    }

    public Cashier acquireCashier() throws RestaurantException {

        try {

            semaphore.acquire();

            Cashier freeCashier;

            LOCK_CASHIERS.lock();
            try {
                freeCashier = cashiers.poll();
            } finally {
                LOCK_CASHIERS.unlock();
            }

            return freeCashier;

        } catch (InterruptedException e) {
            throw new RestaurantException(e);
        }

    }

    public void releaseCashier(Cashier cashier) {

        LOCK_CASHIERS.lock();

        try {

            boolean isCashierComeBack;
            do {
                isCashierComeBack = cashiers.offer(cashier);
            } while (!isCashierComeBack);

        } finally {
            LOCK_CASHIERS.unlock();
        }

        semaphore.release();

    }

}