package com.fabtec.professionalmanagement.unittest.mocks;

import java.util.ArrayList;
import java.util.List;

import com.fabtec.professionalmanagement.api.model.Professional;

public class MockProfessional {

	public Professional mockEntity() {
		return mockEntity(0);
	}

	public Professional mockEntity(Integer number) {
		Professional professional = new Professional();
		professional.setId(number.longValue());
		professional.setFirstName("zé" + number);
		professional.setLastName("pereira da" + number);
		professional.setRegistrationCode("H0976rF" + number);
		//professional.setRegistrationDate(LocalDate.now());

		return professional;
	}
	
	public List<Professional> mockEntityList() {
		List<Professional> professionals = new ArrayList<Professional>();
		for(int i=0; i<14; i++) {
			professionals.add(mockEntity(i));
		}
		return professionals;
	}
	
}
