package com.s4n.homedelivery;

import com.s4n.homedelivery.model.Drone;
import com.s4n.homedelivery.util.FileUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeliveryApp {

    private final int numberOfDrones;

    private final int ordersPerTravel;

    private final ExecutorService executorDrones;

    public DeliveryApp(int numberOfDrones, int ordersPerTravel) {
        this.numberOfDrones = numberOfDrones;
        this.ordersPerTravel = ordersPerTravel;
        executorDrones = Executors.newFixedThreadPool(numberOfDrones);
    }

    public void startOperation() {

        FileUtil.deleteDirectoryContent("out");

        for (int i = 1; i <= numberOfDrones; i++) {
            executorDrones.execute(new Drone(String.valueOf(i), ordersPerTravel));
        }

        executorDrones.shutdown();
    }

    public static void main(String[] args) {
        DeliveryApp delivery = new DeliveryApp(5, 3);
        delivery.startOperation();
    }
}
