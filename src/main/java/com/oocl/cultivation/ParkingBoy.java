package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy extends AbstractParkingBoy{


    public ParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingLot getParkingLotInList(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isParkingLotFull())
                .findFirst()
                .orElse(null);
    }
}
