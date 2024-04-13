package com.example.Rental;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
public class ReservationController {

	@Autowired
	private VehicleRepo vehicleRepo;
	@Autowired
	private ReservationRepo reservationRepo;

	@Autowired
	private UserRepository userRepo;

	// Display all the vehicles in the database
	@GetMapping("/rentVehicle")
	public String rentVehicle(Model model) {
		model.addAttribute("vehicles", vehicleRepo.findAll());
		return "vehiclesForRental";
	}

	// handle vehicle selection when the user wants to rent a vehicle
	@GetMapping("/selectVehicle/{id}")
	public String selectVehicle(@PathVariable(value = "id") long id, Model model) {
		
		// retrive the vehicle details based the id
		Optional<Vehicle> optional = vehicleRepo.findById(id);
		Vehicle vehicle;
		Reservation reservation = new Reservation();
		
		// find the user by their email. Email is retrieved from the context in line 96
		User user = userRepo.findUserByEmail(getLoggedInUser());

		if (optional.isPresent()) {
			vehicle = optional.get();
			// copy logged in user`s details to the rental`s form
			reservation.setFirstName(user.getFirstName());
			reservation.setLastName(user.getLastName());
			reservation.setEmail(user.getEmail());
			// Dsiplay the selected vehicle`model and rate per day
			reservation.setModel(vehicle.getModel());
			reservation.setRate(vehicle.getRatePerDay());

			model.addAttribute("reservation", reservation);
		} else {
			throw new RuntimeException(" Vehicle not found for id :: " + id);
		}
		return "rentVehicle";
	}

	// save Reservation
	@PostMapping("/saveReservation")
	public String saveReservation(@Valid Reservation reservation, BindingResult result) {

		if (result.hasErrors()) {
			return "rentVehicle";
		}
		reservationRepo.save(reservation);
		return "redirect:/rentVehicle";
	}

	// delete reservation, admin only
	@GetMapping("deleteReservation/{id}")
	public String deleteReservation(@PathVariable(value = "id") long id, Model model) {
		reservationRepo.deleteById(id);
		return "redirect:/yourReservation";
	}

	// displays all Reservations users made through their email
	//find the user by their email. Email is retrieved from the context in line 96
	@GetMapping("/yourReservation")
	public String yourReservation(Reservation reservation, Model model, String keyword) {
		List<Reservation> yourReservations = 
				reservationRepo.findReservationByEmail(getLoggedInUser());
			model.addAttribute("yourReservations", yourReservations);		
		return "yourReservation";
	}

	// Allow admin to view all reservations
	@GetMapping("/allReservation")
	public String getAllReservation(Reservation reservation, Model model) {
		List<Reservation> allReservations = reservationRepo.findAll();
		model.addAttribute("allReservations", allReservations);
		return "allReservations";
	}

	// get Logged In User
	private String getLoggedInUser() {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getName();
		return userEmail;
	}
}
