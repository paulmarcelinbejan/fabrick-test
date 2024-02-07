package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class LegalPersonBeneficiary {

	@NotBlank(message = "taxRelief.legalPersonBeneficiary.fiscalCode must not be blank!")
	@Pattern(regexp = "^[0-9]{11}$", message = "taxRelief.fiscalCode invalid format!")
	private String fiscalCode;

	private String legalRepresentativeFiscalCode;

}
