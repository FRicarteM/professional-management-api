package com.fabtec.professionalmanagement.api.controllers;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fabtec.professionalmanagement.api.data.vo.ProfessionalVo;
import com.fabtec.professionalmanagement.api.exceptions.ExceptionResponse;
import com.fabtec.professionalmanagement.api.exceptions.NotFoundException;
import com.fabtec.professionalmanagement.api.services.ProfessionalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/professionals/v1")
@Tag(name = "Professional", description = "EndPoint for managing Professionals")
public class ProfessionalController {

	@Autowired
	ProfessionalService service;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/search",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Finds all Professionals",
		description = "Search and find all professionals",
		tags = {"Search all Professionals"},
		responses = {@ApiResponse( description = "Success",
			responseCode = "200",
			content = { @Content(array = @ArraySchema (schema = @Schema(implementation = ProfessionalVo.class)))}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(  
						array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))}),
				@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
							array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))}),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(  
						array = @ArraySchema(schema = @Schema(implementation = ExceptionResponse.class)))})})
	 
	public List<ProfessionalVo> findAll() throws NotFoundException, FileNotFoundException {
		return service.findAll();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/search/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Finds a Professional",
		description ="Search and find a professional through your id",
		tags = { "Search a Professional" },
		responses = {@ApiResponse( description="Success",
			responseCode="200",
			content = @Content(schema = @Schema( implementation = ProfessionalVo.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400",
				content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(description = "Not Found", responseCode = "404",
				content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(description = "Internal Error", responseCode = "500", 
				content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))})
	public ProfessionalVo findById(@PathVariable(value = "id") Long id) throws NotFoundException, FileNotFoundException {
		return service.findById(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/search-registrationCode/{registrationCode}",
	produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Finds a Professional by his registration code",
	description ="Search and find a professional through your registration code",
	tags = { "Search a Professional" },
	responses = {@ApiResponse( description="Success",
	responseCode="200",
	content = @Content(schema = @Schema( implementation = ProfessionalVo.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(description = "Not Found", responseCode = "404",
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
			@ApiResponse(description = "Internal Error", responseCode = "500", 
			content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))})
	public ProfessionalVo findByRegistrationCode(@PathVariable(value = "registrationCode") String registrationCode) throws NotFoundException, FileNotFoundException {
		return service.findByRegistrationCode(registrationCode);
	}
	 
	
}
