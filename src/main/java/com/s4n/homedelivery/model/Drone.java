package com.s4n.homedelivery.model;

import com.s4n.homedelivery.util.FileUtil;
import com.s4n.homedelivery.util.Util;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Drone implements Runnable {

    private static final String INITIAL_MESSAGE = "== Reporte de entregas ==";
    private final String name;
    private final int ordersPerTravel;
    private Position position;
    private List<String> nextDeliveries;
    private int ordersDeliveredCount;
    private boolean deliverRemainingOrders;

    public Drone(String name, int ordersPerTravel) {
        this.name = name;
        this.ordersPerTravel = ordersPerTravel;
        position = new Position();
        ordersDeliveredCount = 0;
        deliverRemainingOrders = true;
    }

    public void run() {

        loadDeliveries();
        initializeReport();
        nextDeliveries.forEach(this::dispatchDelivery);
    }


    private void loadDeliveries() {

        nextDeliveries = FileUtil.loadFile("in/in" + name + ".txt");
    }

    private void initializeReport() {

        FileUtil.appendFile("/out/out" + name + ".txt", INITIAL_MESSAGE);
    }

    private void dispatchDelivery(String delivery) {

        String route;
        try {
            if (deliverRemainingOrders) {
                processInstructions(delivery);
                route = position.getDirectionReport();
            } else {
                route = "Orden no entregada debido a desajuste de instrucciones";
            }

        } catch (Exception e) {
            route = String.format("Error al procesar las instrucciones: %s", e.getMessage());
            deliverRemainingOrders = false;
        }

        registerReport(route);
        ordersDeliveredCount++;

        if (completeTravel()) {
            comeBackForOrdersAndResetPosition();
        }
    }

    private boolean completeTravel() {
        return ordersDeliveredCount % ordersPerTravel == 0;
    }

    private void comeBackForOrdersAndResetPosition() {
        position = new Position();
        deliverRemainingOrders = true;
        System.out.println("Fin de viaje Drone" + name + ", regresar por más");

        int time = Util.generateTime();
        System.out.println("Time for drone " + name + ": " + time);
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processInstructions(String delivery) {

        for (int i = 0; i < delivery.length(); i++) {
            Character charInstruction = delivery.charAt(i);
            updatePosition(charInstruction);
        }
    }

    private void updatePosition(Character charInstruction) {
        position.updatePosition(charInstruction);
    }

    private void registerReport(String route) {
        FileUtil.appendFile("/out/out" + name + ".txt", route);
    }

}