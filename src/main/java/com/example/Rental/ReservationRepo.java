package com.example.Rental;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long> {
	// search reservations by email	
	List<Reservation> findReservationByEmail( String userEmail);
}

/*

@Query(value = "select * from Reservations r where r.email like %:keyword%", nativeQuery = true)
List<Reservation> findReservationByEmail(@Param("keyword") String keyword);
*/