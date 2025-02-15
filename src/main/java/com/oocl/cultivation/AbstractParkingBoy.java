package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public abstract class AbstractParkingBoy {

    private static final String NOT_ENOUGH_POSITION = "Not enough position.";
    private static final String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please provide your parking ticket.";
    private static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";
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
            lastErrorMessage = NOT_ENOUGH_POSITION;
        } else {
            parkingTicket = getParkingLotInList(parkingLots).park(car);
        }
        return parkingTicket;
    }

    public Car fetch(ParkingTicket ticket) {
        Car fetchedCar = fetchCarFromParkingLots(ticket);
        if (isNull(ticket)) {
            lastErrorMessage = PLEASE_PROVIDE_YOUR_PARKING_TICKET;
        } else if (isNull(fetchedCar)) {
            lastErrorMessage = UNRECOGNIZED_PARKING_TICKET;
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
