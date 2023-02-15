package com.fabtec.professionalmanagement.api.repositories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fabtec.professionalmanagement.api.converters.JsonConverter;
import com.fabtec.professionalmanagement.api.converters.NewProfessional;
import com.fabtec.professionalmanagement.api.exceptions.NotFoundException;
import com.fabtec.professionalmanagement.api.model.Professional;

@Component
public class RepositoryProfessional {
	
	@Value("${resource.path}")
	private String pathWay;

	public List<Professional> findAll() throws NotFoundException, FileNotFoundException {
		return JsonConverter.loadJson(new FileReader(pathWay));
	}

	public Optional<Professional> findById(Long id) throws NotFoundException, FileNotFoundException {

		List<Professional> professionals = new ArrayList<Professional>();
		Professional professional = null;

		professionals = JsonConverter.loadJson(new FileReader(pathWay));
		
		for (Professional prof : professionals) {
			if (prof.getId() == id) {
				professional = NewProfessional.newProfessional(prof);
			} else if (prof.getId() == null) {
				throw new NotFoundException("Not found the register in database!");
			}
		}

		return Optional.ofNullable(professional);

	}

	public Professional findByRegistrationCode(String registrationCode) throws NotFoundException, FileNotFoundException {
		
		List<Professional> professionals = new ArrayList<Professional>();
		Professional professional = null;
		
		professionals = JsonConverter.loadJson(new FileReader(pathWay));
		
		for (Professional prof : professionals) {
			if (prof.getRegistrationCode().equals(registrationCode)) {
				professional = NewProfessional.newProfessional(prof);
			} else if (prof.getRegistrationCode() == null) {
				throw new NotFoundException("Not found the register in database!");
			}
		}
		
		return professional;
		
	}

}
