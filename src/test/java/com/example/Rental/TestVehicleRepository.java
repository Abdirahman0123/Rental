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
public class TestVehicleRepository {
	
	@Autowired
	private VehicleRepo vehicleRepo;
	
	@Test // passed
	public void saveVehicleTest() {
		
		Vehicle vehicle = new Vehicle();
		vehicle.setModel("BWM M1");
		vehicle.setCategory("Hatchback");
		vehicle.setRatePerDay(10);
		vehicle.setYear(2023);
		
		Vehicle savedVehicle = vehicleRepo.save(vehicle);
		
		assertNotNull(savedVehicle);
	}
	

	@Test // passed
	public void updateVehicleTest() {
		Vehicle vehicle =  vehicleRepo.findById(1l).get();		
		vehicle.setYear(2024);		
		Vehicle savedVehicle = vehicleRepo.save(vehicle);	
		assertNotNull(savedVehicle);
		//assertNotNull(savedVehicle);
	}
	
	@Test // passed
	public void getOneVehicleTest() {
		
		// findById(1l).get(); (1l) means 1 long because ID type is Long
		Vehicle vehicle =  vehicleRepo.findById(1l).get();
		assertNotNull(vehicle);
	}
	
	@Test // passed
	public void getAllVehiclesTest() {
		
		List<Vehicle> vehicles =  vehicleRepo.findAll();	
		Assertions.assertThat(vehicles.size()).isGreaterThan(0);
		
	}

}
