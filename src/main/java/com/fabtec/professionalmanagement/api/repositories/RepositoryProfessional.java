package com.fabtec.professionalmanagement.api.repositories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fabtec.professionalmanagement.api.converters.JsonConverter;
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

		List<Professional> professionals = JsonConverter.loadJson(new FileReader(pathWay));
		return professionals.stream()
				.filter(prof -> prof.getId() == id)
				.findFirst();

	}

	public Optional<Professional> findByRegistrationCode(String registrationCode) throws NotFoundException, FileNotFoundException {
		
		List<Professional> professionals = JsonConverter.loadJson(new FileReader(pathWay));
		return professionals.stream()
				.filter(prof -> prof.getRegistrationCode().equals(registrationCode))
				.findFirst();
		
	}

}
