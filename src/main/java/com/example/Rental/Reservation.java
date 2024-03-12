package com.example.Rental;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reservations")
public class Reservation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@NotBlank(message = "First name required.")
    @Column(name = "first_name" , nullable = false)
    private String firstName;

    @NotBlank(message = "Last name required.")
    @Column(name = "last_name" , nullable = false)
    private String lastName;

    @NotBlank(message = "Email required.")
    @Email(message = "Email is not valid")
    @Column(name = "email" , nullable = false)
    private String email;
    
    //@NotEmpty(message = "Model required.")
	@Column(name = "Model" , nullable = false)
	private String model;

	//@Positive(message = "Rate must be positive")
	//@NotEmpty(message = "Price required.")
	@Column(name = "Rate" , nullable = false)
	private int rate;
	
	@Max(value = 7, message = "7 Days Max")
	@Positive(message = "The value must be positive")
	//@NotEmpty(message = "This field required.")
	@Column(name = "Number_Of_Days" , nullable = false)
	private int numberOfDays;
	
	@Column(name = "Total" , nullable = false)
	private int total;
	
}
