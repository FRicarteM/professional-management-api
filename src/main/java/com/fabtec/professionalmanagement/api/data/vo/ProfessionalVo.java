 package com.fabtec.professionalmanagement.api.data.vo;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "first_name", "last_name", "registration_code", "registration_date",})
public class ProfessionalVo extends RepresentationModel<ProfessionalVo> implements Serializable {

	private static final long serialVersionUID = -2105027416192735803L;
	
	@Schema(type = "string", example = "Id of Professional")
	@JsonProperty(value = "id")
	private Long key;
	@Schema(type = "string", example = "First Name of Professional")
	@JsonProperty(value = "first_name")
	private String firstName;
	@Schema(type = "string", example = "Last Name of Professional")
	@JsonProperty(value = "last_name")
	private String lastName;
	@Schema(type = "string", example = "Code Registration of Professional")
	@JsonProperty(value = "registration_code")
	private String registrationCode;
	@Schema(type = "string", example = "Data Resgistration of Professional")
	@JsonProperty(value = "registration_date")
	private LocalDate registrationDate;
		
}