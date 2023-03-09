package com.fabtec.professionalmanagement.unittest.mocks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.fabtec.professionalmanagement.api.data.vo.ProfessionalVo;
import com.fabtec.professionalmanagement.api.model.Professional;

public class MockProfessional {

	public Professional mockEntity() {
		return mockEntity(0);
	}

	public Professional mockEntity(Integer number) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		Professional professional = new Professional();
			professional.setId(number.longValue());
			professional.setFirstName("zé" + number);
			professional.setLastName("pereira da" + number);
			professional.setRegistrationCode("H0976rF" + number);
			professional.setRegistrationDate(LocalDate.now().format(formatter));

		return professional;
	}
	
	public ProfessionalVo mockVo(Integer number) {
		ProfessionalVo professional = new ProfessionalVo();
		professional.setKey(number.longValue());
		professional.setFirstName("zé" + number);
		professional.setLastName("pereira da" + number);
		professional.setRegistrationCode("H0976rF" + number);
		professional.setRegistrationDate(LocalDate.now());
		
		return professional;
	}
	
	public List<Professional> mockEntityList() {
		List<Professional> professionals = IntStream.range(0, 10)
				.mapToObj(i -> mockEntity(i))
				.collect(Collectors.toList());
		return professionals;
	}

	public List<ProfessionalVo> mockVoList() {
		List<ProfessionalVo> professionals = IntStream.range(0, 10)
				.mapToObj(i -> mockVo(i))
				.collect(Collectors.toList());
		return professionals;
	}
	
}
