package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class NaturalPersonBeneficiary {

	@NotBlank(message = "naturalPersonBeneficiary.fiscalCode1 must not be blank!")
	@Pattern(regexp = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", message = "naturalPersonBeneficiary.fiscalCode1 format not valid")
	private String fiscalCode1;

	@Pattern(regexp = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", message = "naturalPersonBeneficiary.fiscalCode2 format not valid")
	private String fiscalCode2;

	@Pattern(regexp = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", message = "naturalPersonBeneficiary.fiscalCode3 format not valid")
	private String fiscalCode3;

	@Pattern(regexp = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", message = "naturalPersonBeneficiary.fiscalCode4 format not valid")
	private String fiscalCode4;

	@Pattern(regexp = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$", message = "naturalPersonBeneficiary.fiscalCode5 format not valid")
	private String fiscalCode5;

}
