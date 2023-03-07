package com.jsp.jspwfm.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.jspwfm.Models.Entities.Car;



public interface CarDao extends JpaRepository<Car,Long> {
	@Query(value = "SELECT * FROM Car  WHERE type = :type", nativeQuery = true)
	public Car getbytype(String type);

}
