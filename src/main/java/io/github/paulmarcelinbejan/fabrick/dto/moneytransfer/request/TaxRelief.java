package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.validator.LegalPersonBeneficiaryValidation;
import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.validator.NaturalPersonBeneficiaryValidation;

import lombok.Data;

@Data
@NaturalPersonBeneficiaryValidation
@LegalPersonBeneficiaryValidation
public class TaxRelief {

	@Pattern(regexp = "^(119R|DL50|L296|L449|L234)?$", message = "Invalid taxReliefId. Valid values are '119R', 'DL50', 'L296', 'L449', 'L234'.")
	private String taxReliefId;

	@NotNull(message = "taxRelief.isCondoUpgrade must not be null!")
	private Boolean isCondoUpgrade;

	// TODO validation on CODICE FISCALE and Partita IVA pattern
	@NotBlank(message = "taxRelief.creditorFiscalCode must not be blank!")
	private String creditorFiscalCode;

	@NotBlank(message = "taxRelief.beneficiaryType must not be blank!")
	@Pattern(regexp = "^(NATURAL_PERSON|LEGAL_PERSON)?$", message = "Invalid beneficiaryType. Valid values are 'NATURAL_PERSON' or 'LEGAL_PERSON'.")
	private String beneficiaryType;

	@Valid
	private NaturalPersonBeneficiary naturalPersonBeneficiary;

	@Valid
	private LegalPersonBeneficiary legalPersonBeneficiary;

}
