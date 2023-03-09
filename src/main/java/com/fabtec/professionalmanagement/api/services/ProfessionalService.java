package com.fabtec.professionalmanagement.api.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabtec.professionalmanagement.api.controllers.ProfessionalController;
import com.fabtec.professionalmanagement.api.converters.VoConverter;
import com.fabtec.professionalmanagement.api.data.vo.ProfessionalVo;
import com.fabtec.professionalmanagement.api.exceptions.NotFoundException;
import com.fabtec.professionalmanagement.api.model.Professional;
import com.fabtec.professionalmanagement.api.repositories.RepositoryProfessional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfessionalService {

	@Autowired
	RepositoryProfessional repository;
	
	public ProfessionalVo findById(Long id) throws NotFoundException, FileNotFoundException {

		log.info("find a professional");
		Professional professional = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Not found the register in database for this ID"));
		ProfessionalVo professionalVo = VoConverter.convertProfessionalToVo(professional);
		professionalVo.add(linkTo(methodOn(ProfessionalController.class).findById(id)).withSelfRel()
				.withType("GET").withName("Find By ID"));
		professionalVo.add(linkTo(methodOn(ProfessionalController.class).findByRegistrationCode(professional.getRegistrationCode()))
				.withRel("Find a Professionals").withType("GET").withName("Find By Registration Code"));
		professionalVo.add(linkTo(methodOn(ProfessionalController.class).findAll()).withRel("All Professionals")
				.withType("GET").withName("Find All"));

		return professionalVo;
	}

	public ProfessionalVo findByRegistrationCode(String registrationCode) throws NotFoundException, FileNotFoundException {
		
		log.info("find a professional by his registration code");
		Professional professional = repository.findByRegistrationCode(registrationCode)
				.orElseThrow(() -> new NotFoundException("Not found the register in database for this Code"));
		ProfessionalVo professionalVo = VoConverter.convertProfessionalToVo(professional);
		
		professionalVo.add(linkTo(methodOn(ProfessionalController.class).findByRegistrationCode(registrationCode))
				.withSelfRel().withType("GET").withName("Find By Registration Code"));
		professionalVo.add(linkTo(methodOn(ProfessionalController.class).findById(professional.getId()))
				.withRel("Find a Professionals").withType("GET").withName("Find By ID"));
		professionalVo.add(linkTo(methodOn(ProfessionalController.class).findAll()).withRel("All Professionals")
				.withType("GET").withName("Find All"));
		
		return professionalVo;
	}

	public List<ProfessionalVo> findAll() throws FileNotFoundException {

		log.info("find all professionals");
		List<ProfessionalVo> model = new ArrayList<>();
		List<ProfessionalVo> professionalsVo = VoConverter
				.convertProfessionalToVoList(repository.findAll());

		for (ProfessionalVo vo : professionalsVo) {
			Long id = vo.getKey();
			String registrationCode = vo.getRegistrationCode();
			vo.add(linkTo(methodOn(ProfessionalController.class).findById(id))
					.withRel("Find a Professionals by Id").withType("GET").withName("Find By ID"));
			vo.add(linkTo(methodOn(ProfessionalController.class).findByRegistrationCode(registrationCode))
					.withRel("Find a Professionals by Registration Code").withType("GET").withName("Find By Registration Code"));
			model.add(vo);
		}

		return model;
	}

}
