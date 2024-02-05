package io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.validator;

import org.apache.commons.lang3.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import io.github.paulmarcelinbejan.fabrick.dto.moneytransfer.request.TaxRelief;

public class LegalPersonBeneficiaryValidator implements ConstraintValidator<LegalPersonBeneficiaryValidation, TaxRelief> {

    @Override
	public boolean isValid(TaxRelief taxRelief, ConstraintValidatorContext context) {
		if (taxRelief != null 
				&& taxRelief.getBeneficiaryType() != null
				&& StringUtils.equals(taxRelief.getBeneficiaryType(), "LEGAL_PERSON")
				&& taxRelief.getLegalPersonBeneficiary() == null) {
			return false;
		}

		return true;
    }
    
}