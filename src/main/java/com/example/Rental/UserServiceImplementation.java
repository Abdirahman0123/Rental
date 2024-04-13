package com.example.Rental;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


// This class implements UserService interface and provides implementatin for its methods
//This class deals finds users by their email/username. It also saves them
@Service
public class UserServiceImplementation implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	public UserServiceImplementation(UserRepository userRepo, RoleRepository roleRepo,
			PasswordEncoder passwordEncoder) {
		userRepository = userRepo;
		roleRepository = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public void saveUser(User user) {		
		user.setPassword(passwordEncoder.encode(user.getPassword())); // lombok not working
		Role role = roleRepository.findRoleByName("ROLE_USER");
		if (role == null) {
			role = checkRoleExist();
		}
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);

	}
	
	// checks if the role exists
	private Role checkRoleExist() {
		Role role = new Role();
		role.setName("ROLE_USER");
		return roleRepository.save(role);
	}
}
