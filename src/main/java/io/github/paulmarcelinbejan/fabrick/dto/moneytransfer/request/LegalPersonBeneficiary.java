package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LegalPersonBeneficiary {

	// TODO validation of Partita IVA pattern
	@NotBlank(message = "taxRelief.legalPersonBeneficiary.fiscalCode must not be blank!")
	private String fiscalCode;

	private String legalRepresentativeFiscalCode;

}
