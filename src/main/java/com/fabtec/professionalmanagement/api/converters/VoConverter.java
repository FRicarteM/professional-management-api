package com.fabtec.professionalmanagement.api.converters;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fabtec.professionalmanagement.api.controllers.ProfessionalController;
import com.fabtec.professionalmanagement.api.data.vo.ProfessionalVo;
import com.fabtec.professionalmanagement.api.exceptions.NotFoundException;
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
	
	public static List<ProfessionalVo> convertProfessionalToVoListWithHateoas(List<Professional> professionals) {
		List<ProfessionalVo> model = new ArrayList<>();
		List<ProfessionalVo> professionalList = professionals.stream()
				.map(VoConverter::convertProfessionalToVo)
				.collect(Collectors.toList());
		
		for (ProfessionalVo vo : professionalList) {
			Long id = vo.getKey();
			String registrationCode = vo.getRegistrationCode();

				try {
					vo.add(linkTo(methodOn(ProfessionalController.class).findById(id))
							.withRel("Find a Professionals by Id").withType("GET").withName("Find By ID"));
					vo.add(linkTo(methodOn(ProfessionalController.class).findByRegistrationCode(registrationCode))
							.withRel("Find a Professionals by Registration Code").withType("GET").withName("Find By Registration Code"));
					model.add(vo);
				} catch (NotFoundException nfe) {
					nfe = new NotFoundException("Not found the register in databasee");
				} catch (FileNotFoundException fnfe) {
					fnfe = new FileNotFoundException("File not found");
				}


		}
		
		return model;
	}

}
