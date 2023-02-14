package com.fabtec.professionalmanagement.api.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Professional implements Serializable {

	private static final long serialVersionUID = -2105027416192735803L;

	private Long id;
	private String firstName;
	private String lastName;
	private String registrationCode;
	private String registrationDate;
		
}