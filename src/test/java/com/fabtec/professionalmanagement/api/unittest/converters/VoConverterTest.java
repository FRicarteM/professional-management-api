package com.fabtec.professionalmanagement.api.unittest.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.fabtec.professionalmanagement.api.converters.VoConverter;
import com.fabtec.professionalmanagement.api.data.vo.ProfessionalVo;
import com.fabtec.professionalmanagement.api.unittest.mocks.MockProfessional;

class VoConverterTest {
	
	MockProfessional input;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockProfessional();
	}

	@Test
	@DisplayName("Tests the method which convert the Professional for ProfessionalVo")
	void testConvertProfessionalToVo() {
		ProfessionalVo output = VoConverter.convertProfessionalToVo(input.mockEntity());
		assertEquals(Long.valueOf(0L), output.getKey());
		assertEquals("zé0", output.getFirstName());
		assertEquals("pereira da0", output.getLastName());
		assertEquals("H0976rF0", output.getRegistrationCode());
		assertEquals(LocalDate.now(), output.getRegistrationDate());
	}

	@Test
	@DisplayName("Tests the method which convert a list of Professional for a list of ProfessionalVo")
	void testConvertProfessionalToVoList() {

		List<ProfessionalVo> outputList = VoConverter.convertProfessionalToVoListWithHateoas(input.mockEntityList());
				
		ProfessionalVo outputZero = outputList.get(0);
		assertEquals(Long.valueOf(0L), outputZero.getKey());
		assertEquals("zé0", outputZero.getFirstName());
		assertEquals("pereira da0", outputZero.getLastName());
		assertEquals("H0976rF0", outputZero.getRegistrationCode());
		assertEquals(LocalDate.now(), outputZero.getRegistrationDate());

		ProfessionalVo outputSeven = outputList.get(7);
		assertEquals(Long.valueOf(7L), outputSeven.getKey());
		assertEquals("zé7", outputSeven.getFirstName());
		assertEquals("pereira da7", outputSeven.getLastName());
		assertEquals("H0976rF7", outputSeven.getRegistrationCode());
		assertEquals(LocalDate.now(), outputSeven.getRegistrationDate());
		
		ProfessionalVo outputNine = outputList.get(9);
		assertEquals(Long.valueOf(9L), outputNine.getKey());
		assertEquals("zé9", outputNine.getFirstName());
		assertEquals("pereira da9", outputNine.getLastName());
		assertEquals("H0976rF9", outputNine.getRegistrationCode());
		assertEquals(LocalDate.now(), outputNine.getRegistrationDate());
	}

}
