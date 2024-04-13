package com.example.Rental;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long>{

	//@Query(value = "select * from Reservations r where r.email like %:keyword%", nativeQuery = true)
	@Query(value = "select * from Vehicles v where v.model like %:keyword%", nativeQuery = true)
	List<Vehicle> findByModel(@Param("keyword") String keyword);
}
