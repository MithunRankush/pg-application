package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {


	@Override
	public String toString() {
		return "User [address=" + address + ", user_id=" + user_id + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", dob=" + dob + ", gender=" + gender + ", phno=" + phno + "]";
	}

	@Embedded
	private Address address;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long user_id;
	private String username;
	private String password;
	private String email;
	private String dob;
	private String gender;
	private long phno;
}