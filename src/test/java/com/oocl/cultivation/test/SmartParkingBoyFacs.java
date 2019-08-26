package com.oocl.cultivation.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.ServiceManager;
import com.oocl.cultivation.SuperSmartParkingBoy;

public class SmartParkingBoyFacs {
	@Test
	void should_not_fetch_any_car_once_call_myself_manage_super_smart_parking_boy_fetch_car_ticket_has_been_used_by_manager() {
		ParkingLot parkingLot = new ParkingLot();
		ServiceManager serviceManager = new ServiceManager(parkingLot);
		SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
		serviceManager.setSuperSmartParkingBoys(superSmartParkingBoy);
		
		serviceManager.callSuperSmartParkingBoyPark(superSmartParkingBoy, new Car());
		ParkingTicket ticket = serviceManager.park(parkingLot, new Car());
		serviceManager.callSuperSmartParkingBoyFetch(superSmartParkingBoy, ticket);

		assertNull(serviceManager.callSuperSmartParkingBoyFetch(superSmartParkingBoy, ticket));
	}

	@Test
	void should_query_error_message_for_used_call_myself_manage_super_smart_parking_boy_fetch_car_ticket_by_manager() {
		ParkingLot parkingLot = new ParkingLot();
		ServiceManager serviceManager = new ServiceManager(parkingLot);
		SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
		serviceManager.setSuperSmartParkingBoys(superSmartParkingBoy);
		
		serviceManager.callSuperSmartParkingBoyPark(superSmartParkingBoy, new Car());
		ParkingTicket ticket = serviceManager.park(parkingLot, new Car());
		serviceManager.callSuperSmartParkingBoyFetch(superSmartParkingBoy, ticket);
		serviceManager.callSuperSmartParkingBoyFetch(superSmartParkingBoy, ticket);

		assertEquals("Unrecognized parking ticket.", serviceManager.getLastErrorMessage());
	}
	
	@Test
	void should_call_myself_manage_super_smart_parking_boy_fetch_cars_to_myself_manage_parking() {
		final int capacity = 3;
		ParkingLot parkingLot = new ParkingLot(capacity);
		ServiceManager serviceManager = new ServiceManager(parkingLot);
		SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
		serviceManager.setSuperSmartParkingBoys(superSmartParkingBoy);

		ParkingTicket parkingTicket = serviceManager.callSuperSmartParkingBoyPark(superSmartParkingBoy, new Car());

		assertNotNull(serviceManager.callSuperSmartParkingBoyFetch(superSmartParkingBoy, parkingTicket));
	}

	@Test
	void should_call_other_manage_super_smart_parking_boy_park_cars_to_myself_manage_parking_boy_of_parkingLot() {
		final int capacity = 3;
		ParkingLot parkingLot1 = new ParkingLot(capacity);
		ParkingLot parkingLot2 = new ParkingLot(capacity);
		ServiceManager serviceManager = new ServiceManager(parkingLot1);
		SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot2);

		serviceManager.callSuperSmartParkingBoyPark(superSmartParkingBoy, new Car());

		assertEquals("This super smart parking boy dont belong with you", serviceManager.getLastErrorMessage());
	}

	@Test
	void should_call_other_manage_super_smart_parking_boy_fetch_cars_to_myself_manage_parking_lot_of_parkingLot() {
		final int capacity = 3;
		ParkingLot parkingLot = new ParkingLot(capacity);

		ServiceManager serviceManager = new ServiceManager(parkingLot);
		ServiceManager serviceManager1 = new ServiceManager(parkingLot);

		SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
		serviceManager1.setSuperSmartParkingBoys(superSmartParkingBoy);
		ParkingTicket parkingTicket = serviceManager1.callSuperSmartParkingBoyPark(superSmartParkingBoy, new Car());
		serviceManager.callSuperSmartParkingBoyFetch(superSmartParkingBoy, parkingTicket);
		assertEquals("This super smart parking boy dont belong with you", serviceManager.getLastErrorMessage());
	}

}
