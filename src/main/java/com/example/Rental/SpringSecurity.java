package com.example.Rental;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Autowired
	private UserDetailsService userDetailsService;

	// encodes the password
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// handles authorisation and access to pages 
	// requestMatchers("/home").permitAll() means allow access to home page for
	// everyone
	// loginPage("/login") is the login page
	// defaultSuccessUrl("/home") users are directed to home page once they login
	// loginProcessingUrl is the page to submit the username and password
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                        .requestMatchers("/home").permitAll()
                        .requestMatchers("/").hasRole("ADMIN")
                        .requestMatchers("/newVehicle").hasRole("ADMIN")
                        .requestMatchers("/saveVehicle").hasRole("ADMIN")
                        .requestMatchers("/updateVehicle/*").hasRole("ADMIN")
                        .requestMatchers("/deleteVehicle/*").hasRole("ADMIN")
                        .requestMatchers("/allReservation").hasRole("ADMIN")
                        .requestMatchers("/rentVehicle").permitAll()
                        .requestMatchers("/selectVehicle/*").hasRole("USER")  
                        .requestMatchers("/saveReservation").hasRole("USER") 
                        .requestMatchers("/deleteReservation/*").hasRole("USER")                  
                        .requestMatchers("/yourReservation").hasRole("USER")
                                
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/home")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

	// creates authenication manager using database authentication
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
