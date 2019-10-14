package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    int getAvailableParkingPosition() {
        return capacity - cars.size();
    }

    double getAvailableParkingPositionRate() {
        return (double) getAvailableParkingPosition() / (double) capacity;
    }

    ParkingTicket park(Car car) {
        if (isParkingLotFull()) {
            return null;
        }

        ParkingTicket parkingTicket = new ParkingTicket();
        cars.put(parkingTicket, car);
        return parkingTicket;
    }

    Car fetchCar(ParkingTicket ticket) {
        Car car = cars.get(ticket);
        cars.remove(ticket);
        return car;
    }

    boolean isParkingLotFull() {
        return cars.size() == capacity;
    }
}
