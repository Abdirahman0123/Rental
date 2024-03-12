package com.example.Rental;

import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Controller
public class VehicleController {

	@Autowired
	private VehicleRepo vehicleRepo;

	// Display all the vehicles in the database
	@GetMapping("/")
	public String home(Model model , String keyword) {
		//model.addAttribute("vehicles", vehicleRepo.findAll());
		//return "vehicles";
		
		if (keyword != null) {
			List<Vehicle> vehicles = vehicleRepo.findByModel(keyword);
			model.addAttribute("vehicles", vehicles);
		}

		else {
			model.addAttribute("vehicles", vehicleRepo.findAll());
		}
		
		return "vehicles";
	}

	// add a new vehicle
	@GetMapping("/newVehicle")
	public String newVehicle(Model model) {
		Vehicle vehicle = new Vehicle();
		model.addAttribute("vehicle", vehicle);
		return "newVehicle";
	}

	// save vehicle
	@PostMapping("/saveVehicle")
	public String saveVehicle(@Valid Vehicle vehicle, BindingResult result) {

		if (result.hasErrors()) {
			return "newVehicle";
		}

		vehicleRepo.save(vehicle);

		return "redirect:/";
	}

	// update vehicle
	@GetMapping("/updateVehicle/{id}")
	public String updateVehicle(@PathVariable(value = "id") long id, Model model) {
		Optional<Vehicle> optional = vehicleRepo.findById(id);
		Vehicle vehicle;

		if (optional.isPresent()) {
			vehicle = optional.get();
			model.addAttribute("vehicle", vehicle);
		} else {
			throw new RuntimeException(" Vehicle not found for id: " + id);
		}
		return "updateVehicle";
	}
	
	// delete vehicle
	@GetMapping("deleteVehicle/{id}")
	public String deleteVehicle(@PathVariable(value = "id") long id, Model model) {
		vehicleRepo.deleteById(id);
		return "redirect:/";
	}
}
