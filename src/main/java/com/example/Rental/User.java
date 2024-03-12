package com.example.Rental;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	// private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "First name required.")
	@Column(nullable = false)
	private String firstName;

	@NotEmpty(message = "Last name required.")
	@Column(nullable = false)
	private String lastName;

	@NotEmpty(message = "Email name required.")
	@Email(message = "Invalid email")
	@Column(nullable = false, unique = true)
	private String email;

	@NotEmpty(message = "Password name required.")
	@Column(nullable = false)
	private String password;

	// many to many relation between users and roles
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
	private List<Role> roles = new ArrayList<>();

	// one to many relations between user and reservation
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn( name = "user_id")
	private List<Reservation> reservations;
}
