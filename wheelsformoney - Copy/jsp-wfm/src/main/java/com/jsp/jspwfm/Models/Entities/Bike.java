package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Bike {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long bike_id;
	@Lob
	private String pic;
	private String bikename;
	private String reg_no;
	private String companyname;
	private int engine;
	private double weight;
	private double mileage;
	private double fueltank;
	private double price;
	private String color;
	private String fueltype;
	private String type;
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="bikeimages",
	joinColumns= {@JoinColumn(name="image")},inverseJoinColumns= {@JoinColumn(name="imageid")})
	private Image image;
	 

}
