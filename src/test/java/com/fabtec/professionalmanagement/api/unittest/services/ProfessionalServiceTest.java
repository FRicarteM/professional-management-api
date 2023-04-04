package com.fabtec.professionalmanagement.api.unittest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;

import com.fabtec.professionalmanagement.api.data.vo.ProfessionalVo;
import com.fabtec.professionalmanagement.api.exceptions.NotFoundException;
import com.fabtec.professionalmanagement.api.model.Professional;
import com.fabtec.professionalmanagement.api.repositories.RepositoryProfessional;
import com.fabtec.professionalmanagement.api.services.ProfessionalService;
import com.fabtec.professionalmanagement.api.unittest.mocks.MockProfessional;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ProfessionalServiceTest {

	MockProfessional input;
	@InjectMocks
	ProfessionalService service;
	@Mock
	RepositoryProfessional repository;
	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockProfessional();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Tests the method which search and return for Professional using a ID")
	void testFindById() throws NotFoundException, FileNotFoundException {
		Professional professional = input.mockEntity(1);
		professional.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(professional));
		
		ProfessionalVo result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.getLinks().toString().contains("</api/professionals/v1/search/1>;rel=\"self\";type=\"GET\";name=\"Find By ID\","
				+ "</api/professionals/v1/search-registrationCode/H0976rF1>;rel=\"Find a Professionals\";type=\"GET\";name=\"Find By Registration Code\","
				+ "</api/professionals/v1/search>;rel=\"All Professionals\";type=\"GET\";name=\"Find All\""));
		assertEquals(Long.valueOf(1L), result.getKey());
		assertEquals("zé1", result.getFirstName());
		assertEquals("pereira da1", result.getLastName());
		assertEquals("H0976rF1", result.getRegistrationCode());
		assertEquals(LocalDate.now(), result.getRegistrationDate());
		
	}
	
	@Test
	@DisplayName("Tests the Not Found Exception throws by the findByID method")
	void testFindByIdNotFoundException() throws NotFoundException, FileNotFoundException{
		NotFoundException exception = assertThrows(NotFoundException.class, () -> service.findById(50L)); 
		
		String expectedMessage = "Not found the register in database for this ID";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@DisplayName("Tests the Not Found Exception throws by the findByRegistrationCode method")
	void testFindByRegistrationCodeNotFoundException() throws NotFoundException, FileNotFoundException{
		NotFoundException exception = assertThrows(NotFoundException.class, () -> service.findByRegistrationCode("XXXXXXX")); 
		
		String expectedMessage = "Not found the register in database for this Code";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	@DisplayName("Tests the method which search and return for Professional using a Registration Code")
	void testFindByRegistrationCode() throws NotFoundException, FileNotFoundException {
		Professional professional = input.mockEntity(1);
		professional.setId(1L);
		
		when(repository.findByRegistrationCode("H0976rF1")).thenReturn(Optional.of(professional));
		
		ProfessionalVo result = service.findByRegistrationCode("H0976rF1");
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.getLinks().toString().contains("</api/professionals/v1/search-registrationCode/H0976rF1>;rel=\"self\";type=\"GET\";name=\"Find By Registration Code\","
				+ "</api/professionals/v1/search/1>;rel=\"Find a Professionals\";type=\"GET\";name=\"Find By ID\","
				+ "</api/professionals/v1/search>;rel=\"All Professionals\";type=\"GET\";name=\"Find All\""));
		assertEquals(Long.valueOf(1L), result.getKey());
		assertEquals("zé1", result.getFirstName());
		assertEquals("pereira da1", result.getLastName());
		assertEquals("H0976rF1", result.getRegistrationCode());
		assertEquals(LocalDate.now(), result.getRegistrationDate());
	}

	@Test
	@DisplayName("Tests the method which search and return for all Professionals")
	void testFindAll() throws FileNotFoundException {
		List<Professional> professionals = input.mockEntityList();
		
		when(repository.findAll()).thenReturn(professionals);
		
		CollectionModel<ProfessionalVo> collectionModel = service.findAll();
		List<ProfessionalVo> result = collectionModel.getContent().stream().collect(Collectors.toList());
		assertNotNull(result);
		assertEquals(10, result.size());
		
		ProfessionalVo resultZero = result.get(0);
		assertNotNull(resultZero);
		assertNotNull(resultZero.getKey());
		assertNotNull(resultZero.getLinks());
		assertTrue(resultZero.getLinks().toString().contains("</api/professionals/v1/search/0>;rel=\"Find a Professionals by Id\";type=\"GET\";name=\"Find By ID\","
				+ "</api/professionals/v1/search-registrationCode/H0976rF0>;rel=\"Find a Professionals by Registration Code\";type=\"GET\";name=\"Find By Registration Code\""));
		assertEquals(Long.valueOf(0L), resultZero.getKey());
		assertEquals("zé0", resultZero.getFirstName());
		assertEquals("pereira da0", resultZero.getLastName());
		assertEquals("H0976rF0", resultZero.getRegistrationCode());
		assertEquals(LocalDate.now(), resultZero.getRegistrationDate());

		ProfessionalVo resultSeven = result.get(7);
		assertNotNull(resultSeven);
		assertNotNull(resultSeven.getKey());
		assertNotNull(resultSeven.getLinks());
		assertTrue(resultSeven.getLinks().toString().contains("</api/professionals/v1/search/7>;rel=\"Find a Professionals by Id\";type=\"GET\";name=\"Find By ID\","
				+ "</api/professionals/v1/search-registrationCode/H0976rF7>;rel=\"Find a Professionals by Registration Code\";type=\"GET\";name=\"Find By Registration Code\""));
		assertEquals(Long.valueOf(7L), resultSeven.getKey());
		assertEquals("zé7", resultSeven.getFirstName());
		assertEquals("pereira da7", resultSeven.getLastName());
		assertEquals("H0976rF7", resultSeven.getRegistrationCode());
		assertEquals(LocalDate.now(), resultSeven.getRegistrationDate());
		
		ProfessionalVo resultNine = result.get(9);
		assertNotNull(resultNine);
		assertNotNull(resultNine.getKey());
		assertNotNull(resultNine.getLinks());
		assertTrue(resultNine.getLinks().toString().contains("</api/professionals/v1/search/9>;rel=\"Find a Professionals by Id\";type=\"GET\";name=\"Find By ID\","
				+ "</api/professionals/v1/search-registrationCode/H0976rF9>;rel=\"Find a Professionals by Registration Code\";type=\"GET\";name=\"Find By Registration Code\""));
		assertEquals(Long.valueOf(9L), resultNine.getKey());
		assertEquals("zé9", resultNine.getFirstName());
		assertEquals("pereira da9", resultNine.getLastName());
		assertEquals("H0976rF9", resultNine.getRegistrationCode());
		assertEquals(LocalDate.now(), resultNine.getRegistrationDate());
	}

}
