package com.fabtec.professionalmanagement.api.converters;

import com.fabtec.professionalmanagement.api.model.Professional;

public class NewProfessional {

	public static Professional newProfessional(Professional professional) {
		
		Professional newProfessional = Professional.builder()
				.id(professional.getId())
				.firstName(professional.getFirstName())
				.lastName(professional.getLastName())
				.registrationCode(professional.getRegistrationCode())
				.registrationDate(professional.getRegistrationDate())
				.build();
		
		return newProfessional;
		
	}

}
