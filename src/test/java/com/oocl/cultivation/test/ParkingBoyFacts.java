package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.stream.Stream;

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
//        given
        Car newCar = new Car();
//        when
        ParkingTicket parkingTicket = parkingBoy.park(newCar);
//        then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_car_by_parking_boy_given_ticket() {
//        given
        Car newCar = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(newCar);
//        when
        Car fetchedCar = parkingBoy.fetch(parkingTicket);
//        then
        assertNotNull(fetchedCar);
    }

    @Test
    void should_park_multiple_cars_when_park_many_cars() {
//        given
        ParkingTicket parkingTicket1 = parkingBoy.park(new Car());
        ParkingTicket parkingTicket2 = parkingBoy.park(new Car());
//        when
        Car fetchedCar1 = parkingBoy.fetch(parkingTicket1);
        Car fetchedCar2 = parkingBoy.fetch(parkingTicket2);
//        then
        assertNotNull(fetchedCar1);
        assertNotNull(fetchedCar2);
    }

    @Test
    void should_return_null_when_fetch_non_existing_ticket() {
//        given
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        ParkingTicket newTicket = new ParkingTicket();
//        when
        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        Car nullCar = parkingBoy.fetch(newTicket);
//        then
        assertNotNull(fetchedCar);
        assertNull(nullCar);
    }

    @Test
    void should_return_null_when_fetch_used_ticket() {
//        given
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
//        when
        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        Car fetchedCarAgain = parkingBoy.fetch(parkingTicket);
//        then
        assertNotNull(fetchedCar);
        assertNull(fetchedCarAgain);
    }

    @Test
    void should_return_null_when_capacity_is_reached_in_parking_lot() {
//        given
        for (int x = 0; x < CAPACITY; x++) {
            parkingBoy.park(new Car());
        }
//        when
        ParkingTicket nullTicket = parkingBoy.park(new Car());
//        then
        assertNull(nullTicket);
    }

    @Test
    void should_return_unrecognized_parking_ticket_message_when_query_error_message() {
//        given
        ParkingTicket newTicket = new ParkingTicket();
//        when
        Car nullCar = parkingBoy.fetch(newTicket);
        String errorMessage = parkingBoy.getLastErrorMessage();
//        then
        assertNull(nullCar);
        assertEquals("Unrecognized parking ticket.", errorMessage);
    }

    @Test
    void should_return_please_provide_your_parking_ticket_message_when_query_error_message() {
//        given
        Car nullCar = parkingBoy.fetch(null);
//        when
        String errorMessage = parkingBoy.getLastErrorMessage();
//        then
        assertNull(nullCar);
        assertEquals("Please provide your parking ticket.", errorMessage);
    }

    @Test
    void should_return_not_enough_position_message_when_query_error_message() {
//        given
        for (int x = 0; x < CAPACITY; x++) {
            parkingBoy.park(new Car());
        }
//        when
        ParkingTicket nullTicket = parkingBoy.park(new Car());
        String errorMessage = parkingBoy.getLastErrorMessage();
//        then
        assertNull(nullTicket);
        assertEquals("Not enough position.", errorMessage);
    }

    @Test
    void should_park_car_to_second_parking_lot_when_first_parking_lot_is_full() {
//        given
        parkingBoy.addParkingLot(new ParkingLot());
        for (int x = 0; x < CAPACITY; x++) {
            parkingBoy.park(new Car());
        }
//        when
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
//        then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_fetch_car_in_respective_parking_lots_when_fetch_car() {
//        given
        parkingBoy.addParkingLot(new ParkingLot());
        for (int x = 0; x < CAPACITY - 1; x++) {
            parkingBoy.park(new Car());
        }
//        when
        ParkingTicket parkingTicketInParkingLot1 = parkingBoy.park(new Car());
        ParkingTicket parkingTicketInParkingLot2 = parkingBoy.park(new Car());

        Car carInParkingLot1 = parkingBoy.fetch(parkingTicketInParkingLot1);
        Car carInParkingLot2 = parkingBoy.fetch(parkingTicketInParkingLot2);
//        then
        assertNotNull(carInParkingLot1);
        assertNotNull(carInParkingLot2);
    }

    @Test
    void should_park_car_in_parking_lot_with_more_empty_positions_when_park_by_smart_parking_boy() {
//        given
        smartParkingBoy.addParkingLot(new ParkingLot());
//        when
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
//        then
        assertNotNull(carInParkingLot1_1);
        assertNotNull(carInParkingLot2_1);

        assertNotNull(carInParkingLot1_2);
        assertNotNull(carInParkingLot2_2);

        assertNotNull(carInParkingLot1_3);
        assertNotNull(carInParkingLot2_3);
    }

    @Test
    void should_park_car_in_parking_lot_with_larger_available_positions_when_park_by_super_smart_parking_boy() {
//       given
        superSmartParkingBoy.addParkingLot(new ParkingLot(15));
//        when
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
//        then
        assertNotNull(carInParkingLot1_1);
        assertNotNull(carInParkingLot2_1);

        assertNotNull(carInParkingLot2_2);
        assertNotNull(carInParkingLot1_2);

        assertNotNull(carInParkingLot2_3);
        assertNotNull(carInParkingLot1_3);
    }
}
