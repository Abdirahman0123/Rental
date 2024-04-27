package com.example.Rental;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestReservationRepository {

	@Autowired
	private ReservationRepo reservationRepo;
	
	@Test 
	public void saveReservationTest() {
		
		Reservation reservation = new Reservation();
		reservation.setFirstName("Jack");
		reservation.setLastName("Mark");
		reservation.setEmail("Jack0983@hotmail.com");
		reservation.setModel("BMW M1");
		reservation.setRate(5);
		reservation.setNumberOfDays(2);
		reservation.setTotal(10);
		
		Reservation savedReservation = reservationRepo.save(reservation);
		assertNotNull(savedReservation);
	}
	

	@Test // passed
	public void updateVehicleTest() {

		Reservation reservation =  reservationRepo.findById(2l).get();
		reservation.setNumberOfDays(5);
		
		Reservation savedReservation = reservationRepo.save(reservation);
		assertNotNull(savedReservation);
		
	}
	
	@Test 
	public void getReservationTest() {
		
		// findById(1l).get(); (1l) means 2 long because ID type is Long
		Reservation reservation =  reservationRepo.findById(2l).get();
		assertNotNull(reservation);
	}
	
	@Test 
	public void getAllVehiclesTest() {
		
		List<Reservation> reservations =  reservationRepo.findAll();
		Assertions.assertThat(reservations.size()).isGreaterThan(0);
		
	}
	
	@Test
	public void findReservationByEmailTest() {
		List<Reservation> reservations =  
				reservationRepo.findReservationByEmail("user1@hotmail.com");
		Assertions.assertThat(reservations.size()).isGreaterThan(0);
	}

}
