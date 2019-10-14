package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public abstract class AbstractParkingBoy {

    private String lastErrorMessage;
    private List<ParkingLot> parkingLots = new ArrayList<>();

    AbstractParkingBoy(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public void addParkingLot(ParkingLot parkingLot) {
        parkingLots.add(parkingLot);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public ParkingTicket park(Car car) {
        boolean parkingLotsAreFull = parkingLots.stream()
                .allMatch(ParkingLot::isParkingLotFull);

        ParkingTicket parkingTicket = null;
        if (parkingLotsAreFull) {
            lastErrorMessage = "Not enough position.";
        } else {
            parkingTicket = getParkingLotInList(parkingLots).park(car);
        }
        return parkingTicket;
    }

    public Car fetch(ParkingTicket ticket) {
        Car fetchedCar = fetchCarFromParkingLots(ticket);
        if (isNull(ticket)) {
            lastErrorMessage = "Please provide your parking ticket.";
        } else if (isNull(fetchedCar)) {
            lastErrorMessage = "Unrecognized parking ticket.";
        }
        return fetchedCar;
    }

    private Car fetchCarFromParkingLots(ParkingTicket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            Car fetchedCar = parkingLot.fetchCar(ticket);
            if (!isNull(fetchedCar)) {
                return fetchedCar;
            }
        }
        return null;
    }

    abstract ParkingLot getParkingLotInList(List<ParkingLot> parkingLots);
}
