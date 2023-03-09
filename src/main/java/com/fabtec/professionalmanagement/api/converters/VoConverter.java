package com.fabtec.professionalmanagement.api.converters;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fabtec.professionalmanagement.api.data.vo.ProfessionalVo;
import com.fabtec.professionalmanagement.api.model.Professional;

public class VoConverter {

	public static ProfessionalVo convertProfessionalToVo(Professional professional){
		
		ProfessionalVo professionalVo = ProfessionalVo.builder()
				.key(professional.getId())
				.firstName(professional.getFirstName())
				.lastName(professional.getLastName())
				.registrationCode(professional.getRegistrationCode())
				.registrationDate(LocalDate.parse(professional.getRegistrationDate()))
				.build();
				
		return professionalVo;
	}
	
	public static List<ProfessionalVo> convertProfessionalToVoList(List<Professional> professionals) {
		List<ProfessionalVo> professionalList = professionals.stream()
				.map(VoConverter::convertProfessionalToVo)
				.collect(Collectors.toList());	
		return professionalList;
	}


}
