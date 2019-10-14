package com.oocl.cultivation;

import java.util.List;

import static java.util.Comparator.comparing;

public class SmartParkingBoy extends AbstractParkingBoy {

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingLot getParkingLotInList(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .max(comparing(ParkingLot::getAvailableParkingPosition))
                .orElse(null);
    }
}
