package com.oocl.cultivation;

import java.util.List;

import static java.util.Comparator.comparing;

public class SuperSmartParkingBoy extends AbstractParkingBoy {
    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    ParkingLot getParkingLotInList(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .max(comparing(ParkingLot::getAvailableParkingPositionRate))
                .orElse(null);
    }

}
