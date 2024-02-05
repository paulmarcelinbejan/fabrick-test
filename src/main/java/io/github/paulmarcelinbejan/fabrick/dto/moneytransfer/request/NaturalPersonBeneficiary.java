package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NaturalPersonBeneficiary {

	// TODO validation of CODICE FISCALE pattern
	@NotBlank(message = "taxRelief.naturalPersonBeneficiary.fiscalCode1 must not be blank!")
	private String fiscalCode1;

	// TODO validation of CODICE FISCALE pattern
	private String fiscalCode2;

	// TODO validation of CODICE FISCALE pattern
	private String fiscalCode3;

	// TODO validation of CODICE FISCALE pattern
	private String fiscalCode4;

	// TODO validation of CODICE FISCALE pattern
	private String fiscalCode5;

}
