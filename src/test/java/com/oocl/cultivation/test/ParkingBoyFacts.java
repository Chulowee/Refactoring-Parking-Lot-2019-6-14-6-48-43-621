package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {

    private static final int CAPACITY = 10;
    private ParkingBoy parkingBoy;
    private SmartParkingBoy smartParkingBoy;
    private SuperSmartParkingBoy superSmartParkingBoy;

    @BeforeEach
    void init() {
        parkingBoy = new ParkingBoy(new ParkingLot());
        smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
    }

    @Test
    void should_return_parking_ticket_when_park_car_by_parking_boy() {
        Car newCar = new Car();

        ParkingTicket parkingTicket = parkingBoy.park(newCar);

        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_car_by_parking_boy_given_ticket() {
        Car newCar = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(newCar);

        Car fetchedCar = parkingBoy.fetch(parkingTicket);

        assertNotNull(fetchedCar);
    }

    @Test
    void should_park_multiple_cars_when_park_many_cars() {
        ParkingTicket parkingTicket1 = parkingBoy.park(new Car());
        ParkingTicket parkingTicket2 = parkingBoy.park(new Car());

        Car fetchedCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);

        assertNotNull(fetchedCar1);
        assertNotNull(fetchedCar2);
    }

    @Test
    void should_return_null_when_fetch_non_existing_ticket() {
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        ParkingTicket newTicket = new ParkingTicket();

        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        Car nullCar = parkingBoy.fetch(newTicket);

        assertNotNull(fetchedCar);
        assertNull(nullCar);
    }

    @Test
    void should_return_null_when_fetch_used_ticket() {
        ParkingTicket parkingTicket = parkingBoy.park(new Car());

        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        Car fetchedCarAgain = parkingBoy.fetch(parkingTicket);

        assertNotNull(fetchedCar);
        assertNull(fetchedCarAgain);
    }

    @Test
    void should_return_null_when_capacity_is_reached_in_parking_lot() {
        for (int x = 0; x < CAPACITY; x++) {
            parkingBoy.park(new Car());
        }

        ParkingTicket nullTicket = parkingBoy.park(new Car());

        assertNull(nullTicket);
    }

    @Test
    void should_return_unrecognized_parking_ticket_message_when_query_error_message() {
        ParkingTicket newTicket = new ParkingTicket();

        Car nullCar = parkingBoy.fetch(newTicket);
        String errorMessage = parkingBoy.getLastErrorMessage();

        assertNull(nullCar);
        assertEquals("Unrecognized parking ticket.", errorMessage);
    }

    @Test
    void should_return_please_provide_your_parking_ticket_message_when_query_error_message() {
        Car nullCar = parkingBoy.fetch(null);

        String errorMessage = parkingBoy.getLastErrorMessage();

        assertNull(nullCar);
        assertEquals("Please provide your parking ticket.", errorMessage);
    }

    @Test
    void should_return_not_enough_position_message_when_query_error_message() {
        for (int x = 0; x < CAPACITY; x++) {
            parkingBoy.park(new Car());
        }

        ParkingTicket nullTicket = parkingBoy.park(new Car());
        String errorMessage = parkingBoy.getLastErrorMessage();

        assertNull(nullTicket);
        assertEquals("Not enough position.", errorMessage);
    }

    @Test
    void should_park_car_to_second_parking_lot_when_first_parking_lot_is_full() {
        parkingBoy.addParkingLot(new ParkingLot());
        for (int x = 0; x < CAPACITY; x++) {
            parkingBoy.park(new Car());
        }

        ParkingTicket parkingTicket = parkingBoy.park(new Car());

        assertNotNull(parkingTicket);
    }

    @Test
    void should_fetch_car_in_respective_parking_lots_when_fetch_car() {
        parkingBoy.addParkingLot(new ParkingLot());
        for (int x = 0; x < CAPACITY - 1; x++) {
            parkingBoy.park(new Car());
        }

        ParkingTicket parkingTicketInParkingLot1 = parkingBoy.park(new Car());
        ParkingTicket parkingTicketInParkingLot2 = parkingBoy.park(new Car());

        Car carInParkingLot1 = parkingBoy.fetch(parkingTicketInParkingLot1);
        Car carInParkingLot2 = parkingBoy.fetch(parkingTicketInParkingLot2);

        assertNotNull(carInParkingLot1);
        assertNotNull(carInParkingLot2);
    }

    @Test
    void should_park_car_in_parking_lot_with_more_empty_positions_when_park_by_smart_parking_boy() {
        smartParkingBoy.addParkingLot(new ParkingLot());

        ParkingTicket parkingTicketInParkingLot1_1 = smartParkingBoy.park(new Car());
        ParkingTicket parkingTicketInParkingLot2_1 = smartParkingBoy.park(new Car());

        ParkingTicket parkingTicketInParkingLot1_2 = smartParkingBoy.park(new Car());
        ParkingTicket parkingTicketInParkingLot2_2 = smartParkingBoy.park(new Car());

        ParkingTicket parkingTicketInParkingLot1_3 = smartParkingBoy.park(new Car());
        ParkingTicket parkingTicketInParkingLot2_3 = smartParkingBoy.park(new Car());

        Car carInParkingLot1_1 = smartParkingBoy.fetch(parkingTicketInParkingLot1_1);
        Car carInParkingLot2_1 = smartParkingBoy.fetch(parkingTicketInParkingLot2_1);

        Car carInParkingLot1_2 = smartParkingBoy.fetch(parkingTicketInParkingLot1_2);
        Car carInParkingLot2_2 = smartParkingBoy.fetch(parkingTicketInParkingLot2_2);

        Car carInParkingLot1_3 = smartParkingBoy.fetch(parkingTicketInParkingLot1_3);
        Car carInParkingLot2_3 = smartParkingBoy.fetch(parkingTicketInParkingLot2_3);

        assertNotNull(carInParkingLot1_1);
        assertNotNull(carInParkingLot2_1);

        assertNotNull(carInParkingLot1_2);
        assertNotNull(carInParkingLot2_2);

        assertNotNull(carInParkingLot1_3);
        assertNotNull(carInParkingLot2_3);
    }

    @Test
    void should_park_car_in_parking_lot_with_larger_available_positions_when_park_by_super_smart_parking_boy() {
        superSmartParkingBoy.addParkingLot(new ParkingLot(15));

        ParkingTicket parkingTicketInParkingLot1_1 = superSmartParkingBoy.park(new Car());
        ParkingTicket parkingTicketInParkingLot2_1 = superSmartParkingBoy.park(new Car());

        ParkingTicket parkingTicketInParkingLot2_2 = superSmartParkingBoy.park(new Car());
        ParkingTicket parkingTicketInParkingLot1_2 = superSmartParkingBoy.park(new Car());

        ParkingTicket parkingTicketInParkingLot2_3 = superSmartParkingBoy.park(new Car());
        ParkingTicket parkingTicketInParkingLot1_3 = superSmartParkingBoy.park(new Car());

        Car carInParkingLot1_1 = superSmartParkingBoy.fetch(parkingTicketInParkingLot1_1);
        Car carInParkingLot2_1 = superSmartParkingBoy.fetch(parkingTicketInParkingLot2_1);

        Car carInParkingLot2_2 = superSmartParkingBoy.fetch(parkingTicketInParkingLot2_2);
        Car carInParkingLot1_2 = superSmartParkingBoy.fetch(parkingTicketInParkingLot1_2);

        Car carInParkingLot2_3 = superSmartParkingBoy.fetch(parkingTicketInParkingLot2_3);
        Car carInParkingLot1_3 = superSmartParkingBoy.fetch(parkingTicketInParkingLot1_3);

        assertNotNull(carInParkingLot1_1);
        assertNotNull(carInParkingLot2_1);

        assertNotNull(carInParkingLot2_2);
        assertNotNull(carInParkingLot1_2);

        assertNotNull(carInParkingLot2_3);
        assertNotNull(carInParkingLot1_3);
    }
}
