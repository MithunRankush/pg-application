package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Car {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long car_id;
  private String carname;
  private String companyname;
  private double price;
  private double mileage;
  private double cubic_capacity;
  private double power;
  private String fueltype;
  private int no_seats;
  private String gearbox;
  private String emission_sticker;
  private String first_reg;
  private String climatisation;
  private String color;
  private String type;
  @OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinTable(name="carimages",
	joinColumns= {@JoinColumn(name="image")},inverseJoinColumns= {@JoinColumn(name="imageid")})
	private Image image;
	 
}
