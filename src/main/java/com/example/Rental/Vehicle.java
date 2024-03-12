package com.example.Rental;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vehicles")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "This field is mandatory")
	//@NotEmpty(message = "Model required.")
	@Column(name = "Model", nullable = false)
	private String model;

	@NotEmpty(message = "This field is mandatory")
	//@NotEmpty(message = "Category required.")
	@Column(name = "Category" , nullable = false)
	private String category;


	@Positive(message = "This value must be positive")
	//@NotEmpty(message = "This required.")
	@Column(name = "Rate Per Day", nullable = false)
	private int ratePerDay;

	@Min(value = 2020, message = "Only vehicles produced after 2020")
	@Positive(message = "Year value must be positive")
	//@NotEmpty(message = "Year required.")
	@Column(name = "Year", nullable = false)
	private int year;

	/*@OneToMany(
	        mappedBy = "vehicle",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	    private List<Reservation> reservation = new ArrayList<>();*/

}
