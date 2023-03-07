package com.jsp.jspwfm.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.jspwfm.Models.Entities.Bike;

public interface BikeDao extends JpaRepository<Bike,Long>{
	 @Query(value = "SELECT * FROM Bike  WHERE type = :type", nativeQuery = true)
		public Bike getbytype(String type);

}
