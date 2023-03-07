package com.jsp.jspwfm.Models.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int imid;
	@Lob
	@Column(nullable=false,columnDefinition="blob")
	private byte[] pic;
	private String type;
	private String name;

	public Image( byte[] pic, String type, String name) {
		super();
		
		this.pic = pic;
		this.type = type;
		this.name = name;
	}


}
